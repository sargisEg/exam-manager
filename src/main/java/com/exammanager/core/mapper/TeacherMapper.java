package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateTeacherRequestDto;
import com.exammanager.core.model.entity.Teacher;
import com.exammanager.core.model.params.CreateTeacherParams;

public interface TeacherMapper {

    CreateTeacherParams map(CreateTeacherRequestDto dto);

    Teacher map(CreateTeacherParams params);
}
