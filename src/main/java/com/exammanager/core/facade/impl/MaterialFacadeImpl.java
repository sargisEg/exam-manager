package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.MaterialFacade;
import com.exammanager.core.mapper.MaterialMapper;
import com.exammanager.core.model.dto.response.MaterialDto;
import com.exammanager.core.model.dto.response.ResourceWithFilename;
import com.exammanager.core.model.entity.Course;
import com.exammanager.core.model.entity.Material;
import com.exammanager.core.model.entity.Student;
import com.exammanager.core.model.params.CreateMaterialParams;
import com.exammanager.core.service.core.CourseService;
import com.exammanager.core.service.core.MaterialService;
import com.exammanager.core.service.core.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class MaterialFacadeImpl implements MaterialFacade {

    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/Documents/exam-files/";

    private final CourseService courseService;
    private final MaterialService materialService;
    private final MaterialMapper materialMapper;
    private final StudentService studentService;


    @Override
    @Transactional
    public MaterialDto uploadMaterial(UserInfo userInfo, MultipartFile file, String courseId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(file, "file should not be null");
        Assert.notNull(courseId, "courseId should not be null");
        log.debug("Uploading file for course with id - {} for provided request", courseId);

        final Course course = courseService.findByIdAndTeacherId(courseId, userInfo.id())
                .orElseThrow(() -> new NotFoundException(
                        "Not found course with id - " + courseId,
                        "Not found course with id - " + courseId
                ));

        final String path;
        log.info("file - {}", file);
        try {
            if (Files.notExists(Paths.get(UPLOAD_DIR))) {
                Files.createDirectory(Paths.get(UPLOAD_DIR));
            }
            Path filePath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            path = filePath.toString();
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final CreateMaterialParams createMaterialParams = new CreateMaterialParams(
                file.getOriginalFilename(),
                path,
                file.getOriginalFilename() == null ? "" : file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1),
                course,
                file.getSize()
        );
        final MaterialDto responseDto = materialMapper.map(materialService.create(createMaterialParams));

        log.debug("Successfully uploaded file for course with id - {} for provided request", courseId);
        return responseDto;
    }

    @Override
    @Transactional
    public ResourceWithFilename downloadMaterial(UserInfo userInfo, String courseId, String materialId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(courseId, "courseId should not be null");
        Assert.notNull(materialId, "courseId should not be null");
        log.trace("Downloading material with id - {} and course id - {}", materialId, courseId);
        final Course course = courseService.findById(courseId)
                .orElseThrow(() -> new NotFoundException(
                        "Not found course with id - " + courseId,
                        "Not found course with id - " + courseId
                ));

        final Optional<Student> optionalStudent = studentService.findById(userInfo.id());
        final boolean isStudent = optionalStudent.isPresent() && optionalStudent.get().getSubgroup().getGroup().getId().equals(course.getGroup().getId());
        final boolean isTeacher = course.getTeacher().getId().equals(userInfo.id());

        if (!isStudent && !isTeacher) {
            throw new NotFoundException(
                    "Not found course with id - " + courseId,
                    "Not found course with id - " + courseId
            );
        }

        final Material material = materialService.findByIdAndCourseId(materialId, courseId)
                .orElseThrow(() -> new NotFoundException(
                        "Not found material with id - " + materialId,
                        "Not found material with id - " + materialId
                ));

        final Path path = Paths.get(material.getLocation());

        try {
            final UrlResource urlResource = new UrlResource(path.toUri());
            if (urlResource.exists()) {
                log.trace("Successfully downloaded material with id - {} and course id - {}", materialId, courseId);
                return new ResourceWithFilename(urlResource, material.getName());
            } else {
                throw new FileNotFoundException("Not found file with uri - " + path.toUri());
            }
        } catch (MalformedURLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaterialDto> getMaterials(UserInfo userInfo, String courseId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(courseId, "courseId should not be null");
        log.trace("Finding materials with course id - {} for provided request", courseId);
        final Course course = courseService.findById(courseId)
                .orElseThrow(() -> new NotFoundException(
                        "Not found course with id - " + courseId,
                        "Not found course with id - " + courseId
                ));

        final Optional<Student> optionalStudent = studentService.findById(userInfo.id());
        final boolean isStudent = optionalStudent.isPresent() && optionalStudent.get().getSubgroup().getGroup().getId().equals(course.getGroup().getId());
        final boolean isTeacher = course.getTeacher().getId().equals(userInfo.id());

        if (!isStudent && !isTeacher) {
            throw new NotFoundException(
                    "Not found course with id - " + courseId,
                    "Not found course with id - " + courseId
            );
        }

        final List<MaterialDto> responseDto = materialService.findByCourseId(courseId).stream()
                .map(materialMapper::map)
                .toList();

        log.trace("Successfully found materials with course id - {} for provided request, response - {}", courseId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public void removeMaterialById(UserInfo userInfo, String courseId, String materialId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(courseId, "courseId should not be null");
        Assert.notNull(materialId, "materialId should not be null");
        log.debug("Removing material with id - {} for provided request", materialId);

        final Optional<Material> optionalMaterial = materialService.findById(materialId);
        if (optionalMaterial.isEmpty()) {
            return;
        }
        final Material material = optionalMaterial.get();
        if (!material.getCourse().getTeacher().getId().equals(userInfo.id())) {
            return;
        }
        try {
            Files.deleteIfExists(Paths.get(material.getLocation()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        materialService.deleteById(materialId);
        log.debug("Successfully removed material with id - {} for provided request", materialId);
    }

    @Override
    @Transactional
    public void removeMaterialByCourseId(UserInfo userInfo, String courseId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(courseId, "courseId should not be null");
        log.debug("Removing materials with course id - {} for provided request", courseId);
        final List<Material> materials = materialService.findByCourseId(courseId);
        materials.forEach(material -> {
            try {
                Files.deleteIfExists(Paths.get(material.getLocation()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        materialService.deleteByCourseId(courseId);
        log.debug("Successfully removed materials with course id - {} for provided request", courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaterialDto> getMaterials(UserInfo userInfo) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.trace("Finding materials for user - {} for provided request", userInfo.id());

        final Optional<Student> optionalStudent = studentService.findById(userInfo.id());
        if (optionalStudent.isEmpty()) {
            throw new IllegalStateException();
        }

        final Student student = optionalStudent.get();
        final String groupId = student.getSubgroup().getGroup().getId();
        final List<MaterialDto> responseDto = courseService.findByGroupId(groupId).stream()
                .map(Course::getId)
                .map(materialService::findByCourseId)
                .flatMap(Collection::stream)
                .map(materialMapper::map)
                .toList();


        log.trace("Successfully found materials for user - {} for provided request, response - {}", userInfo.id(), responseDto);
        return responseDto;
    }
}
