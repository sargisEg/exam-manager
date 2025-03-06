package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateStudentRequestDto;
import com.exammanager.core.model.dto.response.StudentDto;
import com.exammanager.core.model.entity.Student;
import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.model.params.CreateStudentParams;
import com.exammanager.user.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapperImpl implements StudentMapper {

    private final GroupMapper groupMapper;

    @Override
    public Student map(CreateStudentParams params) {
        return new Student(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                params.getFullName(),
                params.getEmail(),
                params.getPhone(),
                params.getPassword(),
                Role.STUDENT,
                params.getSubgroup()
        );
    }

    @Override
    public CreateStudentParams map(CreateStudentRequestDto dto, Subgroup subgroup) {
        return new CreateStudentParams(
                dto.getFullName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getPassword(),
                subgroup
        );
    }

    @Override
    public StudentDto map(Student student) {
        return new StudentDto(
                student.getId(),
                student.getEmail(),
                student.getFullName(),
                student.getPhone(),
                groupMapper.map(student.getSubgroup())
        );
    }
}
