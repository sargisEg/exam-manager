package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.request.CreateTeacherRequestDto;
import com.exammanager.core.model.dto.response.TeacherDto;
import com.exammanager.user.model.dto.response.UserDto;
import org.springframework.data.web.PagedModel;

import java.util.List;

public interface TeacherFacade {


    PagedModel<UserDto> getAllTeachers(UserInfo userInfo, int page, int size);

    UserDto createTeacher(UserInfo userInfo, CreateTeacherRequestDto dto);

    List<UserDto> getAllTeachers(UserInfo userInfo);

    TeacherDto getTeacherById(UserInfo userInfo, String teacherId);
}
