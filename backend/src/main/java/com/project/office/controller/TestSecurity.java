package com.project.office.controller;

import com.project.office.dto.response.ResponseModal;
import com.project.office.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class TestSecurity {
    @Autowired
    private UserService userService;
    @GetMapping("/get-users")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ResponseModal> getAllUsers(){
        return ResponseEntity.ok(ResponseModal.builder().results(Map.of("results", userService.getusers())).build());
    }
}
