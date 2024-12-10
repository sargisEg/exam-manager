package com.exammanager.common.security.jwt;

import com.exammanager.user.model.enums.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.*;

@Slf4j
public class AccessTokenHelper {

    private final Claims claims;
    @Getter
    private final String token;
    private final boolean isValid;

    public AccessTokenHelper(String token) {
        this.token = token;
        final Date now = Date.from(Instant.now());
        this.claims = Jwts.parser()
                //TODO [09/12/2024] [17:52] change
                .verifyWith(Keys.hmacShaKeyFor("sadsdasdasdsadasfsdgnodsknoaincoabfduobwqefuibvbcivbacxjbaiyfdvasvbiasbkd".getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();

        final Date iss = claims.getIssuedAt();
        final Date exp = claims.getExpiration();

        isValid = iss.before(exp) && now.before(exp);
    }

    public boolean isValid() {
        return isValid;
    }

    public <T> T getClaim(String claimName, Class<T> clazz) {
        return Objects.requireNonNull(claims.get(claimName, clazz));
    }

    public String getEmail() {
        return claims.get("email", String.class);
    }

    public String getId() {
        return claims.get("id", String.class);
    }

    public String getSessionId() {
        return claims.get("sessionId", String.class);
    }

    public List<Role> getRoles() {
        final Collection<String> rolesArray = (Collection<String>) claims.get("roles", Collection.class);
        return rolesArray.stream().map(roleName -> Role.valueOf(roleName.split("_")[1])).toList();
    }

}
