package com.exammanager.user.service.core;


import com.exammanager.user.model.entity.UserRoles;

import java.util.List;

public interface UserRolesService {

    List<UserRoles> findByUserId(String userId);
}
