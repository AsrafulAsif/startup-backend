package com.example.startup.startup.springSecurity.service;

import com.example.startup.startup.springSecurity.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenService {
    @Value("${jwt.key}")
    private String jwtKey;
    @Value("${jwt.expiration.millis}")
    private long expirationInMillis;

    public String generateJwtTokenWithInfo(String issuer, String id, String userName,String mobileNumber, Boolean status) {

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuer(issuer)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("userName", userName)
                .claim("mobileNumber", mobileNumber)
                .claim("status", status)
                .signWith(this.getSigningKey(),SignatureAlgorithm.HS256);

        if (expirationInMillis >= 0) {
            long expMillis = System.currentTimeMillis() + expirationInMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public CustomUserDetails verifyTokenAndReturnUserDetails(String token){
        try {
            Claims body = Jwts.parserBuilder()
                    .setSigningKey(this.getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return CustomUserDetails.builder()
                    .id(body.getId())
                    .userName((String) body.get("userName"))
                    .mobileNumber((String) body.get("mobileNumber"))
                    .status((Boolean) body.get("status"))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
