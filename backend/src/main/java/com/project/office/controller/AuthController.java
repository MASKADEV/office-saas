package com.project.office.controller;


import com.project.office.dto.request.Signin;
import com.project.office.dto.response.ResponseModal;
import com.project.office.service.UserService;
import com.project.office.dto.request.RegistrationRequest;
import com.project.office.dto.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser( @Valid @RequestBody RegistrationRequest registrationRequest) {
        log.info("here");
        if (userService.existsByUsername(registrationRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        userService.saveUser(registrationRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseModal> signing(@Valid @RequestBody Signin signin){
        String results = userService.signin(signin);
        if(!results.isEmpty()){
            return ResponseEntity.ok(ResponseModal.builder().results(Map.of("token", results)).statusCode(HttpStatus.ACCEPTED.value()).build());
        }else {
            return ResponseEntity.ok(ResponseModal.builder().message("user not found, try another credential please!").statusCode(HttpStatus.FORBIDDEN.value()).build());
        }
    }
}
