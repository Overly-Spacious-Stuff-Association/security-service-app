package org.example.securityservice.service;

import org.example.securityservice.dto.request.RegisterRequestDto;
import org.example.securityservice.dto.response.RegistrationResponseDto;

public interface AuthenticationService {

    RegistrationResponseDto register(RegisterRequestDto registerRequest);
}
