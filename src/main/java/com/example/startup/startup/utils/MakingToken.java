package com.example.startup.startup.utils;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MakingToken {
    @Value("${jwt.key}")
    private String jwtKey;
    @Value("${jwt.expiration.millis}")
    private long expirationInMillis;


    public String generateJwtTokenWithInfo(String issuer, String id, String userName,String mobileNumber, Boolean status) {

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuer(issuer)
                .claim("name", userName)
                .claim("mobileNumber", mobileNumber)
                .claim("status", status)
                .signWith(SignatureAlgorithm.HS256, jwtKey);

        if (expirationInMillis >= 0) {
            long expMillis = System.currentTimeMillis() + expirationInMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }


}
