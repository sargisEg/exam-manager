package com.exammanager.user.service.core;


import com.exammanager.user.model.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    Optional<User> findById(String userId);
}
