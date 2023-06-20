package com.example.keycloak.security;


import com.example.keycloak.jwt.JwtAuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@EnableGlobalAuthentication
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                  .disable()
                .authorizeHttpRequests()
                  .anyRequest()
                    .authenticated();
        http
                .oauth2ResourceServer()
                   .jwt()
                      .jwtAuthenticationConverter(jwtAuthConverter); // defining my own converter instead of using the default one
        http
                .sessionManagement()
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
