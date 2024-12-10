package com.exammanager.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RefreshTokenHelper {

    private final Claims claims;
    @Getter
    private final String token;

    public RefreshTokenHelper(String token) {
        this.token = token;
        this.claims = Jwts.parser()
                //TODO [09/12/2024] [17:52] change
                .verifyWith(Keys.hmacShaKeyFor("jasndponacknkasndklasnfknwjasndponacknkasndklasnfknwjasndponacknkasndklasnfknw".getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getSessionId() {
        return claims.get("sessionId", String.class);
    }

    public Long getVersion() {
        return claims.get("version", Long.class);
    }

}
