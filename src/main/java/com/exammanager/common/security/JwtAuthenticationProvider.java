package com.exammanager.common.security;

import com.exammanager.auth.model.entity.UserSession;
import com.exammanager.auth.service.core.UserSessionService;
import com.exammanager.common.security.jwt.AccessTokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserSessionService userSessionService;

    //TODO [09/12/2024] [14:11] write proper exception handling for jwt
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final AccessTokenHelper accessTokenHelper = new AccessTokenHelper((String) authentication.getCredentials());
        if (!accessTokenHelper.isValid()) {
            return null;
        }

        final Optional<UserSession> userSession = userSessionService.findById(accessTokenHelper.getSessionId());
        if (userSession.isEmpty() || new Date(userSession.get().getExpAt()).before(new Date())) {
            return null;
        }

        return new JwtAuthenticationToken(accessTokenHelper.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).toList(), accessTokenHelper.getToken());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        final Class<JwtAuthenticationToken> jwtAuthenticationTokenClass = JwtAuthenticationToken.class;
        return authentication.equals(jwtAuthenticationTokenClass);
    }
}
