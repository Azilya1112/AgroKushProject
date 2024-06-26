package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.config.JwtUtils;
import com.example.agrokushproject.dto.AuthenticateRequest;
import com.example.agrokushproject.dto.AuthenticationResponse;
import com.example.agrokushproject.dto.RegisterRequest;
import com.example.agrokushproject.entity.User;
import com.example.agrokushproject.enums.Role;
import com.example.agrokushproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        var user= User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken=jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticateRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()
                )
        );
        var user=userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken=jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }


}
