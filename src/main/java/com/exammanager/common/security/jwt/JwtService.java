package com.exammanager.common.security.jwt;


import com.exammanager.user.model.entity.User;
import com.exammanager.user.model.enums.Role;

import java.util.List;

public interface JwtService {

    String createToken(User user, List<Role> roles, String sessionId);
    String createRefreshToken(String sessionId, long version);
}
