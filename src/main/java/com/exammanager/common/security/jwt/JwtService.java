package com.exammanager.common.security.jwt;


import com.exammanager.user.model.entity.User;

public interface JwtService {

    String createToken(User user, String sessionId);
    String createRefreshToken(String sessionId, long version);
}
