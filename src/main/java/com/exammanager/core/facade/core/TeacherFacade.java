package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.user.model.dto.response.UserDto;
import org.springframework.data.web.PagedModel;

public interface TeacherFacade {


    PagedModel<UserDto> getAllTeachers(UserInfo userInfo, int page, int size);
}
