package com.exammanager.auth.facade;

import com.exammanager.auth.model.dto.request.RefreshRequestDto;
import com.exammanager.auth.model.dto.request.SignInRequestDto;
import com.exammanager.auth.model.dto.response.SignInResponseDto;
import com.exammanager.auth.model.entity.UserSession;
import com.exammanager.auth.service.core.UserSessionService;
import com.exammanager.common.exception.UnauthorizedException;
import com.exammanager.common.security.jwt.JwtService;
import com.exammanager.common.security.jwt.RefreshTokenHelper;
import com.exammanager.user.model.entity.User;
import com.exammanager.user.model.entity.UserRoles;
import com.exammanager.user.model.enums.Role;
import com.exammanager.user.service.core.UserRolesService;
import com.exammanager.user.service.core.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class SignInFacadeImpl implements SignInFacade {

    private final JwtService jwtService;
    private final UserService userService;
    private final UserRolesService userRolesService;
    private final UserSessionService userSessionService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public SignInResponseDto signIn(SignInRequestDto dto) {
        Assert.notNull(dto, "dto should not be null");
        log.debug("Signing in user with email - {}", dto.getEmail());

        final Optional<User> optionalUser = userService.findByEmail(dto.getEmail());
        if (optionalUser.isEmpty())
            throw new UnauthorizedException(
                    "Not found user with email - " + dto.getEmail(),
                    "Wrong username or password"
            );

        final User user = optionalUser.get();
        final List<Role> roles = userRolesService.findByUserId(user.getId()).stream().map(UserRoles::getRole).toList();

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new UnauthorizedException(
                    "Password mismatch for user with email - " + dto.getEmail(),
                    "Wrong username or password"
            );
        }

        final UserSession userSession = userSessionService.createUserSession(user);

        final String token = jwtService.createToken(user, roles, userSession.getId());
        final String refreshToken = jwtService.createRefreshToken(userSession.getId(), userSession.getUpdatedAt());

        log.debug("Successfully signed in user with email - {}", dto.getEmail());
        return new SignInResponseDto(token, refreshToken);
    }

    @Override
    public SignInResponseDto refresh(RefreshRequestDto dto) {
        Assert.notNull(dto, "dto should not be null");
        log.debug("Refreshing token for user - {}", dto.getEmail());

        final RefreshTokenHelper refreshTokenHelper;
        try {
            refreshTokenHelper = new RefreshTokenHelper(dto.getRefreshToken());
        } catch (Exception e) {
            throw new UnauthorizedException("Error while trying to decode refresh token", "Invalid refresh token");
        }
        final String sessionId = refreshTokenHelper.getSessionId();
        final Long version = refreshTokenHelper.getVersion();

        final Optional<UserSession> optionalUserSession = userSessionService.findById(sessionId);
        if (optionalUserSession.isEmpty())
            throw new UnauthorizedException("Not found session with id - " + sessionId, "Invalid refresh token");
        final UserSession userSession = optionalUserSession.get();

        if (new Date(userSession.getExpAt()).before(new Date())) {
            throw new UnauthorizedException("Session was expired session id - " + sessionId, "Invalid refresh token");
        }

        if (!userSession.getUpdatedAt().equals(version)) {
            throw new UnauthorizedException("Refresh token was used session id - " + sessionId, "Invalid refresh token");
        }

        final User user = userSession.getUser();
        final List<Role> roles = userRolesService.findByUserId(user.getId()).stream().map(UserRoles::getRole).toList();
        final Optional<UserSession> optionalUpdatedUserSession = userSessionService.updateUserSession(userSession.getId());
        if (optionalUpdatedUserSession.isEmpty()) {
            //This should never happen
            throw new UnauthorizedException("Something went wrong while updating token", "Invalid refresh token");
        }

        final String token = jwtService.createToken(user, roles, userSession.getId());
        final String refreshToken = jwtService.createRefreshToken(userSession.getId(), optionalUpdatedUserSession.get().getUpdatedAt());

        log.debug("Successfully refreshed token for user - {}", dto.getEmail());
        return new SignInResponseDto(token, refreshToken);
    }
}
