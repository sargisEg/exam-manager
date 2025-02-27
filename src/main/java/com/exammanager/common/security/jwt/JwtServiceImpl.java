package com.exammanager.common.security.jwt;

import com.exammanager.user.model.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Component
public class JwtServiceImpl implements JwtService {

    @Override
    public String createToken(User user, String sessionId) {
        log.trace("Creating token for user - {}", user);
        final Instant now = Instant.now();
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor("sadsdasdasdsadasfsdgnodsknoaincoabfduobwqefuibvbcivbacxjbaiyfdvasvbiasbkd".getBytes()))
                .claim("email", user.getEmail())
                .claim("id", user.getId())
                .claim("sessionId", sessionId)
                .claim("role", user.getRole())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(5, ChronoUnit.MINUTES)))
                .compact();
    }

    @Override
    public String createRefreshToken(String sessionId, long version) {
        log.trace("Creating refresh token for sessionId - {}", sessionId);
        final Instant now = Instant.now();
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor("jasndponacknkasndklasnfknwjasndponacknkasndklasnfknwjasndponacknkasndklasnfknw".getBytes()))
                .claim("sessionId", sessionId)
                .claim("version", version)
                .issuedAt(Date.from(now))
                .compact();
    }
}
