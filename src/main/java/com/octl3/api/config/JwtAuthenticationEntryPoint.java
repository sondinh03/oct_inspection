package com.octl3.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.octl3.api.commons.OctResponse;
import com.octl3.api.commons.exception.ErrorMessage;
import com.octl3.api.commons.exception.ErrorMessages;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        ErrorMessage errorMessage = ErrorMessages.UNAUTHENTICATED;

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        OctResponse<?> octResponse = OctResponse.build(errorMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        response.getWriter().write(objectMapper.writeValueAsString(octResponse));
        response.flushBuffer();
    }
}
