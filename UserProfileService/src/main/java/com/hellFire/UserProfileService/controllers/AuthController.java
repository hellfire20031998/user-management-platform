package com.hellFire.UserProfileService.controllers;

import com.hellFire.UserProfileService.models.requests.LoginRequest;
import com.hellFire.UserProfileService.models.requests.SignUpRequest;
import com.hellFire.UserProfileService.services.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("Health Check", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @NotNull @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.login(loginRequest.getUsername(), loginRequest.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @NotNull @RequestBody SignUpRequest signUpRequest) {
        return new ResponseEntity<>(authService.signUp(signUpRequest.getUsername(), signUpRequest.getPassword()), HttpStatus.OK);
    }
}
