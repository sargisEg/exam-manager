package com.exammanager.user.service.impl;

import com.exammanager.user.model.entity.UserRoles;
import com.exammanager.user.repository.UserRolesRepository;
import com.exammanager.user.service.core.UserRolesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRolesServiceImpl implements UserRolesService {

    private final UserRolesRepository userRolesRepository;

    @Override
    public List<UserRoles> findByUserId(String userId) {
        Assert.hasText(userId, "userId should not be null or empty");
        log.trace("Finding roles of user with id - {}", userId);
        final List<UserRoles> roles = userRolesRepository.findByUserId(userId);
        log.trace("Successfully found roles of user with id - {}, roles - {}", userId, roles);
        return roles;
    }
}
