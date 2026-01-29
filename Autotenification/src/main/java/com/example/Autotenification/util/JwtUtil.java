package com.example.Autotenification.util;

import com.example.Autotenification.model.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}") // тут мб указать полностью как в пропертис
    private String secret;

    @Value("${jwt.expiration:86400000}") // 24 часа
    private long expiration;

    private SecretKey getSigningKey(){
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return new javax.crypto.spec.SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String generateToken(AuthUser user){
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("personalNumber", user.getPersonalNumber())
                .claim("name", user.getName())
                .claim("surname", user.getSurname())
//                .claim("role", user.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    private JwtParser getJwtParser(){
        return Jwts.parser().verifyWith(getSigningKey()).build();
    }

    public boolean isTokenValid(String token){
        try{
            Claims claims = getJwtParser().parseSignedClaims(token).getPayload();
            return !claims.getExpiration().before(new Date()); // Проверяем expiration
        } catch (Exception e){
            return false;
        }
    }

    private Claims getClaimsFromToken(String token){
        return getJwtParser().parseSignedClaims(token).getPayload();
    }

    public String getEmailFromToken(String token){
        return getClaimsFromToken(token).getSubject();
    }


/*    public String getRoleFromToken(String token){
        return getClaimsFromToken(token).get("role", String.class);
    }*/



}
