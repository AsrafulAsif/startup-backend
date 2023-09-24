package com.example.startup.startup.config.springSecurity;

import com.example.startup.startup.service.JwtTokenService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    final JwtTokenService jwtTokenService;
    @Override
    protected void doFilterInternal(
             @NonNull HttpServletRequest request,
             @NonNull HttpServletResponse response,
             @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

     if (StringUtils.isEmpty(header) || !header.startsWith("Bearer ")){
         filterChain.doFilter(request, response);
         return;
     }
     final String token = header.split(" ")[1].trim();
     CustomUserDetails userDetails = jwtTokenService.verifyTokenAndReturnUserDetails(token);

     if (userDetails == null){
         filterChain.doFilter(request,response);
         return;
     }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.getAuthorities()
        );

     authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

     SecurityContextHolder.getContext().setAuthentication(authenticationToken);
     filterChain.doFilter(request,response);
    }
}
