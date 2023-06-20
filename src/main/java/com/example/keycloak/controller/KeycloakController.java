package com.example.keycloak.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class KeycloakController {

    @GetMapping
    @PreAuthorize("hasRole('client_user')")
    public String hello() {
        return "Hello from keycloak Spring Boot project1. Secure end-point";
    }

    @GetMapping("/hello")
    @PreAuthorize("hasRole('client_admin')")
    public String hello1() {
        return  "Hello from keycloak Spring Boot Project1 - ADMIN";
    }
}
