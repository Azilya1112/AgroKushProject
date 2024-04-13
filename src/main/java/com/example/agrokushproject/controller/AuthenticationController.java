package com.example.agrokushproject.controller;



import com.example.agrokushproject.dto.AuthenticateRequest;
import com.example.agrokushproject.dto.AuthenticationResponse;
import com.example.agrokushproject.dto.RegisterRequest;
import com.example.agrokushproject.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
            ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticateRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
