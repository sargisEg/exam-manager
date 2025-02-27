package com.exammanager.user.mapper;

import com.exammanager.user.model.dto.response.UserDto;
import com.exammanager.user.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto map(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getFullName(), user.getPhone(), user.getRole().name());
    }
}
