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
        log.trace("Creating course with params - {}", params);

        Course courseFromParams = courseMapper.map(params);
        courseFromParams.setId(uuidProvider.getUuid());

        final Course course = courseRepository.save(courseFromParams);

        log.trace("Successfully created course with params - {}, result - {}", params, course);
        return course;
    }

    @Override
    public Optional<Course> findByIdAndGroupId(String courseId, String groupId) {
        log.trace("Finding course with id - {}, in group - {}", courseId, groupId);

        Optional<Course> course = courseRepository.findByIdAndGroupId(courseId, groupId);

        log.trace("Successfully found course with id - {}, in group - {}, result - {}", courseId, groupId, course);
        return course;
    }

    @Override
    public Page<Course> findByGroupId(String groupId, int page, int size) {
        log.trace("Finding courses in group - {}", groupId);

        final Page<Course> courses = courseRepository.findByGroupId(groupId, PageRequest.of(page, size));

        log.trace("Successfully found courses in group - {}, result - {}", groupId, courses);
        return courses;
    }

    @Override
    public void deleteByIdAndGroupId(String courseId, String groupId) {

    }

    @Override
    public List<Course> findByTeacherId(String teacherId) {
        log.trace("Finding courses with teacher id - {}", teacherId);

        final List<Course> courses = courseRepository.findByTeacherId(teacherId);

        log.trace("Successfully found courses with teacher id - {}, result - {}", teacherId, courses);
        return courses;
    }
}
