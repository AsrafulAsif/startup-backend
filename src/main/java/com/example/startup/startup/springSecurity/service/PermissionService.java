package com.example.startup.startup.springSecurity.service;

import com.example.startup.startup.springSecurity.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service("permissionService")
@Slf4j
public class PermissionService {

    private final HttpServletRequest request;

    @Autowired
    public PermissionService(HttpServletRequest request) {
        this.request = request;
    }

    public boolean hasPermission() {
        String  requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        log.info(requestURI);
        log.info(requestMethod);
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getStatus().equals(true);
    }
}
