package org.example.securityservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponseDto {
    private boolean success;
    private String message;
    private Instant timestamp;

    public static RegistrationResponseDto success(String message) {
        return RegistrationResponseDto.builder()
                .success(true)
                .message(message)
                .timestamp(Instant.now())
                .build();
    }
}
