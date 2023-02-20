package com.project.office.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthenticationResource {
    @PostMapping("/signin")
    public String signin(){
        return "signin";
    }

    @PostMapping("/register")
    public String register(){
        return "register";
    }
}
