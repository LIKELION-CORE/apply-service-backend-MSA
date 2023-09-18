package com.springboot.apply_service.domain.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class JwtProvider {
    private final String HEADER_NAME = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    public String getToken(HttpServletRequest request) {
        String header = request.getHeader(HEADER_NAME);
        String accessToken = "";
        if (header != null && header.startsWith(TOKEN_PREFIX)){
            accessToken = header.replace(TOKEN_PREFIX, "");
        }
        return accessToken;
    }
}