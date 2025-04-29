package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.request.CreateStudentRequestDto;
import com.exammanager.core.model.dto.response.StudentDto;

import java.util.List;

public interface StudentFacade {

    StudentDto createStudent(UserInfo userInfo, CreateStudentRequestDto dto);

    StudentDto getStudent(UserInfo userInfo, String studentId);

    List<StudentDto> getStudentsByGroupId(UserInfo userInfo, String groupId);

    void removeStudent(UserInfo userInfo, String studentId);

    //Teacher

    List<StudentDto> getStudentsByGroupIdAndTeacherId(UserInfo userInfo, String groupId);
}
