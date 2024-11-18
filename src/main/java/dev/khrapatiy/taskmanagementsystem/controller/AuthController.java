package dev.khrapatiy.taskmanagementsystem.controller;

import dev.khrapatiy.taskmanagementsystem.dto.request.UserDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.TokensResponse;
import dev.khrapatiy.taskmanagementsystem.dto.response.ValidateErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication", description = "Регистрация, авторизация и валидация пользователей.")
public interface AuthController {
    @Operation(method = "POST", summary = "Регистрация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно зарегистрирован."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка регистрации пользователя.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ValidateErrorResponse.class)
                    )
            )
    })
    ResponseEntity<Void> signUp(@Valid @RequestBody UserDto userDTO);

    @Operation(method = "POST", summary = "Авторизация пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно авторизовался.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TokensResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка авторизации пользователя.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ValidateErrorResponse.class)
                    )
            )
    })
    ResponseEntity<TokensResponse> signIn(@Valid @RequestBody UserDto userDTO);

    @Operation(method = "POST", summary = "Выход пользователя.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно вышел."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Пользователь не авторизован."
            )
    })
    @SecurityRequirement(name = "JWT")
    ResponseEntity<Void> signOut();
}