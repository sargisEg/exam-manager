package com.exammanager.user.facade;

import com.exammanager.user.model.dto.response.UserDto;

public interface UserFacade {

    UserDto me(String userId);
}
