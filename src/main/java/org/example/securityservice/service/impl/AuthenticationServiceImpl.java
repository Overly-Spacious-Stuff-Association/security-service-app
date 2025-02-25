package org.example.securityservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.securityservice.dto.request.RegisterRequestDto;
import org.example.securityservice.dto.response.RegistrationResponseDto;
import org.example.securityservice.service.AuthenticationService;
import org.example.securityservice.service.grpc.UserServiceClient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserServiceClient userServiceClient;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegistrationResponseDto register(RegisterRequestDto registerRequest) {
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        return userServiceClient.createUser(registerRequest);
    }
}
