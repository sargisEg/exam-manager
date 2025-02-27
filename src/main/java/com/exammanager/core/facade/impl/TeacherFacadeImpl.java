package com.exammanager.core.facade.impl;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.TeacherFacade;
import com.exammanager.core.model.entity.Teacher;
import com.exammanager.core.service.core.TeacherService;
import com.exammanager.user.mapper.UserMapper;
import com.exammanager.user.model.dto.response.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeacherFacadeImpl implements TeacherFacade {

    private final TeacherService teacherService;
    private final UserMapper userMapper;

    @Override
    public PagedModel<UserDto> getAllTeachers(UserInfo userInfo, int page, int size) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.trace("Getting all teacher for provided request, user - {}", userInfo.id());

        final Page<Teacher> departments = teacherService.findAll(page, size);
        final Page<UserDto> p = departments
                .map(userMapper::map);
        PagedModel<UserDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got all teacher for provided request, response - {}", responseDto);
        return responseDto;
    }
}
