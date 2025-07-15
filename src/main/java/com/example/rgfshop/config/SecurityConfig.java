package com.example.rgfshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", 
                    "/landing.html",
                    "/login.html", 
                    "/register.html", 
                    "/index.html",
                    "/js/**", 
                    "/css/**", 
                    "/images/**", 
                    "/api/auth/**"
                ).permitAll()
                .anyRequest().permitAll() 
            )
            .formLogin().disable()
            .httpBasic().disable();

        return http.build();
    }
}
