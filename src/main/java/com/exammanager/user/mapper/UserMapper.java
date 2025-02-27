package com.exammanager.user.mapper;

import com.exammanager.user.model.dto.response.UserDto;
import com.exammanager.user.model.entity.User;

public interface UserMapper {

    UserDto map(User user);
}
