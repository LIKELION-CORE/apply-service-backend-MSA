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

@Configuration
public class HeaderConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

            if (requestAttributes instanceof ServletRequestAttributes) {
                HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

                if (request != null) {

                    String token = request.getHeader("Authorization");
                    System.out.println("apply-server token:"+token);
                    if (token != null && token.startsWith("Bearer ")) {
                        token = token.substring(7);
                        System.out.println("apply-server subString token:"+token);

                        template.header(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                    }
                }
            }
        };
    }
}