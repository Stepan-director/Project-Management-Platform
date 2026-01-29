package com.example.Task.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

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


    private JwtParser getJwtParser(){
        return Jwts.parser().verifyWith(getSigningKey()).build();
    }

    public boolean isTokenValid(String token){
        try{
            Claims claims = getJwtParser().parseSignedClaims(token).getPayload();
            return !claims.getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }

    private Claims getClaimsFromToken(String token){
        return getJwtParser().parseSignedClaims(token).getPayload();
    }

//    public String getEmailFromToken(String token){
//        return getClaimsFromToken(token).getSubject();
//    }

    public String getPersonalNumber(String token){
        return getClaimsFromToken(token).get("personalNumber", String.class);
    }

/*    public String getRoleFromToken(String token){
        return getClaimsFromToken(token).get("role", String.class);
    }*/



}
