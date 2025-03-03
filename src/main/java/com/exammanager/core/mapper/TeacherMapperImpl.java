package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateTeacherRequestDto;
import com.exammanager.core.model.entity.Teacher;
import com.exammanager.core.model.params.CreateTeacherParams;
import com.exammanager.user.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public CreateTeacherParams map(CreateTeacherRequestDto dto) {
        return new CreateTeacherParams(
                dto.getFullName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getPassword()
        );
    }

    @Override
    public Teacher map(CreateTeacherParams params) {
        return new Teacher(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                params.getFullName(),
                params.getEmail(),
                params.getPhone(),
                params.getPassword(),
                Role.TEACHER
        );
    }
}
