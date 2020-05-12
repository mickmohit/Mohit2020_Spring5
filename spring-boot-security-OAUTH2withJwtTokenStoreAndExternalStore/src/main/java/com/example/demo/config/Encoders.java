package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Encoders {

    @Bean(name = "oauthClientPasswordEncoder")
    public BCryptPasswordEncoder oauthClientPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean(name = "userPasswordEncoder")
    public BCryptPasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
