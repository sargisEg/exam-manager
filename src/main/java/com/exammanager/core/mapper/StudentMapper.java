package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateStudentRequestDto;
import com.exammanager.core.model.dto.response.StudentDto;
import com.exammanager.core.model.entity.Student;
import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.model.params.CreateStudentParams;

public interface StudentMapper {

    StudentDto map(Student student);

    Student map(CreateStudentParams params);

    CreateStudentParams map(CreateStudentRequestDto dto, Subgroup subgroup);
}
