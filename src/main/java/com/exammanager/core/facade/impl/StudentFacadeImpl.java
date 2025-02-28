package com.exammanager.core.facade.impl;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.StudentFacade;
import com.exammanager.core.model.dto.request.CreateStudentRequestDto;
import com.exammanager.user.model.dto.response.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentFacadeImpl implements StudentFacade {
    @Override
    public UserDto createTeacher(UserInfo userInfo, CreateStudentRequestDto dto) {
        return null;
    }

    @Override
    public PagedModel<UserDto> getAllTeachers(UserInfo userInfo, int page, int size) {
        return null;
    }
}
