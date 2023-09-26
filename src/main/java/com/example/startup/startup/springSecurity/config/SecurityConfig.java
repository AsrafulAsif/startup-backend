package com.example.startup.startup.springSecurity.config;

import com.example.startup.startup.springSecurity.JwtTokenFilter;
import com.example.startup.startup.springSecurity.RestAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true
)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**")
                        .permitAll()
                        .requestMatchers("api/v1/user/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint(restAuthenticationEntryPoint))
                .addFilterBefore(
                        jwtTokenFilter,
                        UsernamePasswordAuthenticationFilter.class
        );
        return httpSecurity.build();
    }
}
