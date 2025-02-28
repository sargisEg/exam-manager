package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateCourseRequestDto;
import com.exammanager.core.model.dto.response.CourseDto;
import com.exammanager.core.model.entity.Course;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.entity.Teacher;
import com.exammanager.core.model.params.CreateCourseParams;

public interface CourseMapper {

    CreateCourseParams map(Group group, Teacher teacher, CreateCourseRequestDto dto);

    CourseDto map(Course course);

    Course map(CreateCourseParams params);
}
