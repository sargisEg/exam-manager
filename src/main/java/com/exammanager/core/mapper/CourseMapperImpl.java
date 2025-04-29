package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateCourseRequestDto;
import com.exammanager.core.model.dto.response.CourseDto;
import com.exammanager.core.model.entity.Course;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.entity.Teacher;
import com.exammanager.core.model.params.CreateCourseParams;
import com.exammanager.user.mapper.UserMapper;
import com.exammanager.utils.GroupUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseMapperImpl implements CourseMapper {

    private final GroupMapper groupMapper;
    private final UserMapper userMapper;

    @Override
    public CreateCourseParams map(Group group, Teacher teacher, CreateCourseRequestDto dto) {
        return new CreateCourseParams(
                dto.getName(),
                group,
                teacher
        );
    }

    @Override
    public CourseDto map(Course course) {
        return new CourseDto(
                course.getId(),
                course.getName(),
                course.getSemester(),
                groupMapper.map(course.getGroup()),
                userMapper.map(course.getTeacher())
        );
    }

    @Override
    public Course map(CreateCourseParams params) {
        final Group group = params.getGroup();
        return new Course(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                params.getName(),
                GroupUtils.getGroupSemester(group),
                group,
                params.getTeacher()
        );
    }
}
