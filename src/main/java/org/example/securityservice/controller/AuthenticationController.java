package org.example.securityservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.securityservice.dto.request.RegisterRequestDto;
import org.example.securityservice.dto.response.RegistrationResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationController {

    @Operation(summary = "Регистрация нового пользователя")
    ResponseEntity<RegistrationResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto);
}
