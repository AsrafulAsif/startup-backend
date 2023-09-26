package com.example.startup.startup.service;

import com.example.startup.startup.config.springSecurity.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service("permissionService")
public class PermissionService {
    public boolean hasPermission() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getStatus().equals(true);
    }
}
