package com.exammanager.auth.service.impl;

import com.exammanager.auth.model.entity.UserSession;
import com.exammanager.auth.repository.UserSessionRepository;
import com.exammanager.auth.service.core.UserSessionService;
import com.exammanager.common.service.UuidProvider;
import com.exammanager.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSessionServiceImpl implements UserSessionService {

    private final UserSessionRepository userSessionRepository;
    private final UuidProvider uuidProvider;

    @Override
    public UserSession createUserSession(User user) {
        Assert.notNull(user, "user should not be null");
        log.trace("Creating user session for user - {}", user);

        final UserSession userSession = new UserSession(
                uuidProvider.getUuid(),
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                user,
                //TODO [09/12/2024] [17:56] change
                Instant.now().plus(5, ChronoUnit.DAYS).toEpochMilli()
        );

        userSessionRepository.save(userSession);

        log.trace("Successfully created user session for user - {}, session - {}", user, userSession);
        return userSession;
    }

    @Override
    public Optional<UserSession> updateUserSession(String id) {
        Assert.hasText(id, "id should not be null");
        log.trace("Updating user session with id - {}", id);
        final Optional<UserSession> optionalSession = userSessionRepository.findById(id);
        if (optionalSession.isEmpty()) {
            return Optional.empty();
        }
        final UserSession session = optionalSession.get();
        session.setUpdatedAt(System.currentTimeMillis());
        session.setExpAt(Instant.now().plus(5, ChronoUnit.DAYS).toEpochMilli());
        final UserSession updatedSession = userSessionRepository.save(session);
        log.trace("Successfully updated user session with id - {}", id);
        return Optional.of(updatedSession);
    }

    @Override
    public Optional<UserSession> findById(String id) {
        Assert.hasText(id, "id should not be null");
        log.trace("Finding session with id - {}", id);
        final Optional<UserSession> userSession = userSessionRepository.findById(id);
        log.trace("Successfully found session with id - {}, session - {}", id, userSession);
        return userSession;
    }

    @Override
    public List<UserSession> findByUserId(String userId) {
        Assert.hasText(userId, "userId should not be null");
        log.trace("Finding sessions with userId - {}", userId);
        final List<UserSession> userSessions = userSessionRepository.findByUserId(userId);
        log.trace("Successfully found sessions with userId - {}, sessions - {}", userId, userSessions);
        return userSessions;
    }

    @Override
    public Optional<UserSession> findByUserIdAndId(String userId, String id) {
        Assert.hasText(userId, "userId should not be null");
        Assert.hasText(id, "id should not be null");
        log.trace("Finding sessions with userId - {} and id - {}", userId, id);
        final Optional<UserSession> userSession = userSessionRepository.findByUserIdAndId(userId, id);
        log.trace("Successfully found sessions with userId - {} and id - {}, sessions - {}", userId, id, userSession);
        return userSession;
    }

}
