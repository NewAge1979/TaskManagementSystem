package dev.khrapatiy.taskmanagementsystem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Токены: access и refresh.", requiredProperties = {"accessToken", "refreshToken"})
public record TokensResponse(
        @Schema(description = "Access токен.", accessMode = Schema.AccessMode.AUTO)
        String accessToken,
        @Schema(description = "Refresh токен.", accessMode = Schema.AccessMode.AUTO)
        String refreshToken
) {
}