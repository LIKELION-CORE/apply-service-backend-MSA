package com.springboot.apply_service.client;

import com.google.common.net.HttpHeaders;
import com.springboot.apply_service.domain.auth.JwtProvider;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HeaderConfiguration {
//    JwtProvider jwtProvider;
//    @Autowired
//    public HeaderConfiguration(JwtProvider jwtProvider){
//        this.jwtProvider = jwtProvider;
//    }
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

            if (requestAttributes != null) {
                HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

                if (request != null) {
                    JwtProvider jwtProvider = new JwtProvider();
                    String token = jwtProvider.getToken(request);
                    if (token != null) {
                        //String token = (String) request.getSession().getAttribute(SessionUser.TOKEN_KEY);
                        template.header(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                    }
                }
            }
        };
    }
}
