package com.ins.sys.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
public class SessionConfig {
    @Bean
    public ServletListenerRegistrationBean httpSessionEventPublisher() {
        ServletListenerRegistrationBean<HttpSessionEventPublisher> registration = new ServletListenerRegistrationBean<>();
        registration.setListener(new HttpSessionEventPublisher());
        return registration;
    }
}
