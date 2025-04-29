package com.exammanager.core.service.impl;

import com.exammanager.common.service.UuidProvider;
import com.exammanager.core.mapper.CourseMapper;
import com.exammanager.core.model.entity.Course;
import com.exammanager.core.model.params.CreateCourseParams;
import com.exammanager.core.repository.CourseRepository;
import com.exammanager.core.service.core.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final UuidProvider uuidProvider;

    @Override
    public Course create(CreateCourseParams params) {
        Assert.notNull(params, "params should not be null");
        log.trace("Creating course with params - {}", params);

        Course courseFromParams = courseMapper.map(params);
        courseFromParams.setId(uuidProvider.getUuid());

        final Course course = courseRepository.save(courseFromParams);

        log.trace("Successfully created course with params - {}, result - {}", params, course);
        return course;
    }

    @Override
    public Optional<Course> findByIdAndGroupId(String courseId, String groupId) {
        Assert.hasText(groupId, "groupId should not be null");
        Assert.hasText(courseId, "courseId should not be null");
        log.trace("Finding course with id - {}, in group - {}", courseId, groupId);

        Optional<Course> course = courseRepository.findByIdAndGroupId(courseId, groupId);

        log.trace("Successfully found course with id - {}, in group - {}, result - {}", courseId, groupId, course);
        return course;
    }

    @Override
    public Page<Course> findByGroupId(String groupId, int page, int size) {
        Assert.hasText(groupId, "groupId should not be null");
        log.trace("Finding courses page in group - {}", groupId);

        final Page<Course> courses = courseRepository.findByGroupId(groupId, PageRequest.of(page, size));

        log.trace("Successfully found courses page in group - {}, result - {}", groupId, courses);
        return courses;
    }

    @Override
    public List<Course> findByGroupId(String groupId) {
        Assert.hasText(groupId, "groupId should not be null");
        log.trace("Finding courses in group - {}", groupId);

        final List<Course> courses = courseRepository.findByGroupId(groupId);

        log.trace("Successfully found courses in group - {}, result - {}", groupId, courses);
        return courses;
    }


    @Override
    public List<Course> findByTeacherId(String teacherId) {
        Assert.hasText(teacherId, "teacherId should not be null");
        log.trace("Finding courses with teacher id - {}", teacherId);

        final List<Course> courses = courseRepository.findByTeacherId(teacherId);

        log.trace("Successfully found courses with teacher id - {}, result - {}", teacherId, courses);
        return courses;
    }


    @Override
    public void deleteById(String courseId) {
        Assert.hasText(courseId, "courseId should not be null");
        log.trace("Deleting course with id - {}" , courseId);
        courseRepository.deleteById(courseId);
        log.trace("Successfully deleted course with id - {}" , courseId);
    }

    @Override
    public void deleteByTeacherId(String teacherId) {
        Assert.hasText(teacherId, "teacherId should not be null");
        log.trace("Deleting courses with teacher id - {}" , teacherId);
        courseRepository.deleteByTeacherId(teacherId);
        log.trace("Successfully deleted courses with teacher id - {}" , teacherId);
    }

    @Override
    public Optional<Course> findByIdAndTeacherId(String courseId, String teacherId) {
        Assert.hasText(teacherId, "teacherId should not be null");
        Assert.hasText(courseId, "courseId should not be null");
        log.trace("Finding course with id - {} and teacher id - {}", courseId, teacherId);

        final Optional<Course> courses = courseRepository.findByIdAndTeacherId(courseId, teacherId);

        log.trace("Successfully found course with id - {} and teacher id - {}, result - {}", courseId, teacherId, courses);
        return courses;

    }

    @Override
    public Optional<Course> findById(String courseId) {
        Assert.hasText(courseId, "courseId should not be null");
        log.trace("Finding course with id - {}", courseId);

        final Optional<Course> course = courseRepository.findById(courseId);

        log.trace("Successfully found course with id - {}, result - {}", courseId, course);
        return course;
    }
}
