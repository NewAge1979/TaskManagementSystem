package dev.khrapatiy.taskmanagementsystem.controller;

import dev.khrapatiy.taskmanagementsystem.dto.request.UserDTO;
import dev.khrapatiy.taskmanagementsystem.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "auth", description = "Регистрация и аутентификация пользователей.")
public interface AuthController {
    @Operation(method = "POST", summary = "Регистрация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно зарегистрирован."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка регистрации пользователя.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<Void> signUp(@RequestBody @Valid UserDTO userDTO);
}