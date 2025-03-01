package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.request.CreateStudentRequestDto;
import com.exammanager.core.model.dto.request.StudentRequestFilter;
import com.exammanager.core.model.dto.response.StudentDto;
import org.springframework.data.web.PagedModel;

import java.util.List;

public interface StudentFacade {

    StudentDto createStudent(UserInfo userInfo, CreateStudentRequestDto dto);

    PagedModel<StudentDto> getAllStudents(UserInfo userInfo, StudentRequestFilter filter, int page, int size);

    List<StudentDto> getAllStudents(UserInfo userInfo, StudentRequestFilter filter);

    StudentDto getStudent(UserInfo userInfo, String studentId);
}
