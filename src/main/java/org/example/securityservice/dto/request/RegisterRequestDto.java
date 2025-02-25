package org.example.securityservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @NotBlank(message = "Email обязателен для заполнения")
    @Email(message = "Некорректный формат email")
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$",
            message = "Пароль должен содержать минимум 8 символов, цифру, заглавную и строчную буквы, спецсимвол")
    private String password;

    @NotNull(message = "Идентификатор роли обязателен")
    @Positive(message = "Идентификатор роли должен быть положительным числом")
    private Integer roleId;

    @NotBlank(message = "Имя обязательно")
    @Size(min = 1, max = 40, message = "Длина имени должна быть от 1 до 40 символов")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    @Size(min = 1, max = 40, message = "Длина фамилии должна быть от 1 до 40 символов")
    private String lastName;

    @NotNull(message = "Дата рождения обязательна")
    @Past(message = "Дата рождения должна быть в прошлом")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
}
