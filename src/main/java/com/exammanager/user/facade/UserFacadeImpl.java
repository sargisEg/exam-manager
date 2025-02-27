package com.exammanager.user.facade;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.user.mapper.UserMapper;
import com.exammanager.user.model.dto.response.UserDto;
import com.exammanager.user.model.entity.User;
import com.exammanager.user.service.core.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserDto me(String userId) {
        log.trace("Finding user with id - {}, for provided request", userId);

        User user = userService.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                        "Cannot find user with id - " + userId,
                        "User does not exist"
                ));

        final UserDto userDto = userMapper.map(user);
        log.trace("Successfully found user with id - {}, response - {}", userId, userDto);
        return userDto;
    }
}
