package dev.khrapatiy.taskmanagementsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Данные пользователя.", requiredProperties = {"email", "password"})
@Data
public class UserDTO {
    @Schema(description = "E-mail пользователя.", example = "user@email.com", accessMode = Schema.AccessMode.AUTO)
    @Email(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "E-mail имеет не корректный формат: ${validatedValue}.")
    private String email;

    @Schema(description = "Пароль пользователя.", example = "p@$$w0rd", accessMode = Schema.AccessMode.AUTO)
    @Size(min = 8, max = 32, message = "Пароль должен содержать от 8 до 32 символов.")
    private String password;
}