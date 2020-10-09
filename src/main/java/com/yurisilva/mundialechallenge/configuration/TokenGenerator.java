package com.yurisilva.mundialechallenge.configuration;

import com.yurisilva.mundialechallenge.dto.response.UserResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

public class TokenGenerator {

    public String generateToken(UserResponse user) {
        byte[] signingKey = SecurityConstant.JWT_SECRET.getBytes();
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstant.TOKEN_TYPE)
                .setIssuer(SecurityConstant.TOKEN_ISSUER)
                .setAudience(SecurityConstant.TOKEN_AUDIENCE)
                .claim("username", user.getUsername())
                .claim("name", user.getName())
                .claim("authorities", user.getAuthorities())
                .setExpiration(new Date(new Date().getTime() + java.util.concurrent.TimeUnit.MINUTES.toMillis(SecurityConstant.EXPIRATION)))
                .compact();
    }
}
