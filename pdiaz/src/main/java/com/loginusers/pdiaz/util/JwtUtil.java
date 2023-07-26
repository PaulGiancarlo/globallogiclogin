package com.loginusers.pdiaz.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String secret = generateSafeToken();
    private final Long expiration = 21600L;
    
    public String generateToken(String userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("userId", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    private String generateSafeToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[36];
        random.nextBytes(bytes);
        var encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytes);
    }
}
