package dev.khrapatiy.taskmanagementsystem.controller;

import dev.khrapatiy.taskmanagementsystem.dto.request.CreateTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.request.EditTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.TaskResponse;
import dev.khrapatiy.taskmanagementsystem.enums.Priority;
import dev.khrapatiy.taskmanagementsystem.enums.Status;
import dev.khrapatiy.taskmanagementsystem.utils.validate.ValueOfEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к ресурсу запрещен.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
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
                    responseCode = "403",
                    description = "Доступ к ресурсу запрещен.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задача не найдена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @SecurityRequirement(name = "JWT")
    ResponseEntity<TaskResponse> showTask(
            @Parameter(description = "Id задачи.", example = "1")
            @PathVariable
            Long taskId
    );

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
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к ресурсу запрещен.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задача не найдена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @SecurityRequirement(name = "JWT")
    ResponseEntity<TaskResponse> updateTask(
            @Parameter(description = "Id задачи.", example = "1")
            @PathVariable
            Long taskId,
            @Valid
            @RequestBody
            EditTaskDto taskDto
    );

    @Operation(method = "DELETE", summary = "Удаление задачи.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Задача успешно удалена."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка удаления задачи.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к ресурсу запрещен.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задача не найдена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @SecurityRequirement(name = "JWT")
    ResponseEntity<Void> deleteTask(
            @Parameter(description = "Id задачи.", example = "1")
            @PathVariable
            Long taskId
    );

    @Operation(method = "PATCH", summary = "Изменение статуса задачи.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Статус успешно изменен.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка изменения статуса.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к ресурсу запрещен.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задача не найдена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @SecurityRequirement(name = "JWT")
    ResponseEntity<TaskResponse> changeStatus(
            @Parameter(description = "Id задачи.", example = "1")
            @RequestParam
            @Valid
            @NotNull(message = "Не указан id задачи.")
            Long taskId,
            @Parameter(description = "Статус задачи.", example = "RUNNING")
            @RequestParam
            @Valid
            @NotNull(message = "Не указано новое значение статуса.")
            @ValueOfEnum(
                    enumClass = Status.class,
                    message = "Новый статус указан не верно. Укажите в качестве статуса одно этих значений: NEW, FINISHED, AWAITING, RUNNING."
            )
            String status
    );

    @Operation(method = "PATCH", summary = "Изменение приоритета задачи.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Приоритет успешно изменен.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка изменения приоритета.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к ресурсу запрещен.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задача не найдена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @SecurityRequirement(name = "JWT")
    ResponseEntity<TaskResponse> changePriority(
            @Parameter(description = "Id задачи.", example = "1")
            @RequestParam
            @Valid
            @NotNull(message = "Не указан id задачи.")
            Long taskId,
            @Parameter(description = "Приоритет задачи.", example = "HIGH")
            @RequestParam
            @Valid
            @NotNull(message = "Не указано новое значение приоритета.")
            @ValueOfEnum(
                    enumClass = Priority.class,
                    message = "Новый приоритет указан не верно. Укажите в качестве приоритета одно этих значений: LOW, NORMAL, HIGH."
            )
            String priority
    );

    @Operation(method = "PATCH", summary = "Изменение исполнителя по задачи.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Исполнитель успешно изменен.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка изменения исполнителя.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к ресурсу запрещен.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задача не найдена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @SecurityRequirement(name = "JWT")
    ResponseEntity<TaskResponse> setExecutor(
            @Parameter(description = "Id задачи.", example = "1")
            @RequestParam
            @Valid
            @NotNull(message = "Не указан id задачи.")
            Long taskId,
            @Parameter(description = "Id исполнителя.", example = "2")
            @RequestParam
            @Valid
            @NotNull(message = "Не указан id исполнителя.")
            Long executorId
    );
}