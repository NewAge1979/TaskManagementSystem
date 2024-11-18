package dev.khrapatiy.taskmanagementsystem.controller;

import dev.khrapatiy.taskmanagementsystem.dto.response.ValidateErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Task", description = "Управление задачами.")
public interface TaskController {
    @Operation(method = "POST", summary = "Создание задачи.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Задача успешно создана.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema()
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка создания задачи.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ValidateErrorResponse.class)
                    )
            )
    })
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<?> createTask();
}
