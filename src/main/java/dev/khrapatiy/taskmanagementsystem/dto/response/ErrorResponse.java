package dev.khrapatiy.taskmanagementsystem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сообщение об ошибки.", requiredProperties = {"errorMessage"})
public record ErrorResponse(
        @Schema(description = "Текст сообщения.", example = "Текст сообщения об ошибке.", accessMode = Schema.AccessMode.AUTO)
        String errorMessage
) {
}