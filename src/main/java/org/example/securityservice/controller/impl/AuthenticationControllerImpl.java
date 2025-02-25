package org.example.securityservice.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.securityservice.controller.AuthenticationController;
import org.example.securityservice.dto.request.RegisterRequestDto;
import org.example.securityservice.dto.response.RegistrationResponseDto;
import org.example.securityservice.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<RegistrationResponseDto> register(@RequestBody @Valid RegisterRequestDto registerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authenticationService.register(registerRequest));
    }
}
