package dev.khrapatiy.taskmanagementsystem.dto.response;

import dev.khrapatiy.taskmanagementsystem.dto.Violation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Сообщение об ошибках валидации данных.", requiredProperties = {"errorMessages"})
public record ValidateErrorResponse(
        @Schema(description = "Список ошибок валидации данных.", accessMode = Schema.AccessMode.AUTO)
        List<Violation> errorMessages
) {
}