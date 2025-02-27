package com.exammanager.common.security;

import com.exammanager.auth.model.entity.UserSession;
import com.exammanager.auth.service.core.UserSessionService;
import com.exammanager.common.security.jwt.AccessTokenHelper;
import com.exammanager.user.model.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserSessionService userSessionService;

    //TODO [09/12/2024] [14:11] write proper exception handling for jwt
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final AccessTokenHelper accessTokenHelper;
        try {
            accessTokenHelper = new AccessTokenHelper((String) authentication.getCredentials());
        } catch (Exception e) {
            throw new BadCredentialsException("Unauthorized");
        }
        if (!accessTokenHelper.isValid()) {
            throw new BadCredentialsException("Unauthorized");
        }

        final Optional<UserSession> userSession = userSessionService.findById(accessTokenHelper.getSessionId());
        if (userSession.isEmpty() || new Date(userSession.get().getExpAt()).before(new Date())) {
            return null;
        }
        final String email = accessTokenHelper.getEmail();
        final String id = accessTokenHelper.getId();
        final Role role = accessTokenHelper.getRole();
        UserInfoProvider.setUserInfo(new UserInfo(id, email, role));

        return new JwtAuthenticationToken(List.of(new SimpleGrantedAuthority("ROLE_" + role.name())), accessTokenHelper.getToken());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        final Class<JwtAuthenticationToken> jwtAuthenticationTokenClass = JwtAuthenticationToken.class;
        return authentication.equals(jwtAuthenticationTokenClass);
    }
}
