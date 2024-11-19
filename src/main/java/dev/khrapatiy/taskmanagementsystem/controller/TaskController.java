package dev.khrapatiy.taskmanagementsystem.controller;

import dev.khrapatiy.taskmanagementsystem.dto.request.CreateTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.request.EditTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.ErrorResponse;
import dev.khrapatiy.taskmanagementsystem.dto.response.TaskResponse;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Task", description = "Управление задачами.")
public interface TaskController {
    @Operation(method = "POST", summary = "Создание задачи.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Задача успешно создана.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class)
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
    ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskDto taskDto);

    @Operation(method = "GET", summary = "Просмотр задачи.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Задача успешно просмотрена.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка при просмотри задачи.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ValidateErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задача не найдена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @SecurityRequirement(name = "JWT")
    ResponseEntity<TaskResponse> showTask(@PathVariable(name = "taskId") Long taskId);

    @Operation(method = "Patch", summary = "Обновление задачи.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Задача успешно обновлена.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка обновления задачи.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ValidateErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задача не найдена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @SecurityRequirement(name = "JWT")
    ResponseEntity<TaskResponse> updateTask(@PathVariable(name = "taskId") Long taskId, @Valid @RequestBody EditTaskDto taskDto);

    @Operation(method = "DELETE", summary = "Удаление задачи.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Задача успешно удалена.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema()
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка удаления задачи.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ValidateErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задача не найдена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @SecurityRequirement(name = "JWT")
    ResponseEntity<Void> deleteTask(@PathVariable(name = "taskId") Long taskId);
}