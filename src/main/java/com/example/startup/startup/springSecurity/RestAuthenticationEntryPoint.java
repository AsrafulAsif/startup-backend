package com.example.startup.startup.springSecurity;

import com.example.startup.startup.model.SimpleResponseRest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;


@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        SimpleResponseRest simpleResponseRest = new SimpleResponseRest();
        simpleResponseRest.message = "Unauthorized access.";
        simpleResponseRest.statusCode = HttpServletResponse.SC_UNAUTHORIZED;

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, simpleResponseRest);
        out.flush();
    }
}
