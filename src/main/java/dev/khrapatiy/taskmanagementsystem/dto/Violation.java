package dev.khrapatiy.taskmanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Описание ошибки валидации данных.", requiredProperties = {"fieldName", "message"})
public record Violation(
        @Schema(description = "Наименование поля.", example = "Наименование поля.", accessMode = Schema.AccessMode.AUTO)
        String fieldName,
        @Schema(description = "Описание ошибки.", example = "Описание ошибки.", accessMode = Schema.AccessMode.AUTO)
        String message
) {
}