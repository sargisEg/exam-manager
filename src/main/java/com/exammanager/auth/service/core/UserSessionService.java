package com.exammanager.auth.service.core;


import com.exammanager.auth.model.entity.UserSession;
import com.exammanager.user.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserSessionService {

    UserSession createUserSession(User user);
    Optional<UserSession> updateUserSession(String id);

    Optional<UserSession> findById(String id);
    List<UserSession> findByUserId(String userId);
    Optional<UserSession> findByUserIdAndId(String userId, String id);
}
