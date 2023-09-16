package com.example.startup.startup.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class MakingPasswordHash {
    public static String makingPasswordHash(String password){
        String salt = BCrypt.gensalt(10);
        return BCrypt.hashpw(password,salt);
    }
}
