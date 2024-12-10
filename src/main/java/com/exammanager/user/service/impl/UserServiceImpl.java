package com.exammanager.user.service.impl;

import com.exammanager.user.model.entity.User;
import com.exammanager.user.repository.UserRepository;
import com.exammanager.user.service.core.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository<User> userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        Assert.hasText(email, "email should not be null or empty");
        log.trace("Finding user with email - {}", email);
        final Optional<User> optionalUser = userRepository.findByEmail(email);
        log.trace("Successfully found user with email - {}, user - {}", email, optionalUser);
        return optionalUser;
    }
}
