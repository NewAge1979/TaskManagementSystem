package dev.khrapatiy.taskmanagementsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(description = "Данные пользователя.", requiredProperties = {"email", "password"})
@Data
public class UserDto {
    @Schema(description = "E-mail пользователя.", example = "user@email.com", accessMode = Schema.AccessMode.AUTO)
    @NotNull(message = "Необходимо указать e-mail.")
    @NotEmpty(message = "Необходимо указать e-mail.")
    @Email(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "E-mail имеет не корректный формат: ${validatedValue}.")
    private String email;

    @Schema(description = "Пароль пользователя.", example = "p@$$w0rd", accessMode = Schema.AccessMode.AUTO)
    @NotNull(message = "Необходимо указать пароль.")
    @NotEmpty(message = "Необходимо указать пароль.")
    @Size(min = 8, max = 32, message = "Пароль должен содержать от 8 до 32 символов.")
    private String password;
}