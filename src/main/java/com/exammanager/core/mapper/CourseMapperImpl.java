package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateCourseRequestDto;
import com.exammanager.core.model.dto.response.CourseDto;
import com.exammanager.core.model.entity.Course;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.params.CreateCourseParams;
import org.springframework.stereotype.Component;

@Component
public class CourseMapperImpl implements CourseMapper {
    @Override
    public CreateCourseParams map(Group group, CreateCourseRequestDto dto) {
        return null;
    }

    @Override
    public CourseDto map(Course course) {
        return null;
    }
}
