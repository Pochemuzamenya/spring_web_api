package org.filatov.crmapp.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.filatov.crmapp.domain.Manager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expirationTime;

    public String extractUsername(String authToken) {
        return getClaimsByToken(authToken)
                .getSubject();
    }

    public Claims getClaimsByToken(String authToken) {
        String key = Base64.getEncoder().encodeToString(authToken.getBytes());
        return Jwts.parserBuilder()
                // TODO: 24.10.2022 Заменить метод принимающий строку на масив байт
                .setSigningKey(key)
                .build()
                .parseClaimsJws(authToken)
                .getBody();
    }

    public boolean validateToken(String authToken) {
        return getClaimsByToken(authToken)
                .getExpiration()
                .before(new Date());
    }

    public String generateToken(Manager manager) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", List.of(manager.getRole()));
        // TODO: 24.10.2022 Заменить на нормальные даты
        long expirationSeconds = Long.parseLong(expirationTime);
        Date creationDate = new Date();
        Date expirationDate = new Date(creationDate.getTime() + expirationSeconds * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(manager.getUsername())
                .setIssuedAt(creationDate)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();

    }
}
