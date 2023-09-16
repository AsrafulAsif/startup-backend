package com.example.startup.startup.utils;


import com.example.startup.startup.exception.UnAuthorizeException;
import com.example.startup.startup.model.ClientInfo;
import io.jsonwebtoken.*;
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
                .claim("userName", userName)
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

    public ClientInfo verifyTokenWithInfo(String authorization) {
        if ( authorization == null ) throw new UnAuthorizeException("No token");
        String[] splitAuthorization = authorization.split(" ");
        if ( splitAuthorization.length < 2 ) throw new UnAuthorizeException("Bad token format");
        String token = splitAuthorization[1];
        ClientInfo clientInfo = new ClientInfo();
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(jwtKey)
                    .parseClaimsJws(token)
                    .getBody();
            clientInfo.id = body.getId();
            clientInfo.userName= (String) body.get("userName");
            clientInfo.mobileNumber = (String) body.get("mobileNumber");
            clientInfo.status = (Boolean) body.get("status");
            if (!clientInfo.status) throw new UnAuthorizeException("You are no longer our member.");
            return clientInfo;
        } catch (Exception e) {
            throw new UnAuthorizeException("Bad token");
        }
    }


}
