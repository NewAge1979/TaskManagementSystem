package dev.khrapatiy.taskmanagementsystem.controller;

import dev.khrapatiy.taskmanagementsystem.dto.response.TasksListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Tasks List", description = "Вывод списка задач.")
public interface TasksListController {
    @Operation(method = "GET", summary = "Список задач.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список сформирован.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TasksListResponse.class)
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
    ResponseEntity<Page<TasksListResponse>> getTaskList(
            @Parameter(description = "Страница.", example = "0")
            @RequestParam
            Integer page,
            @Parameter(description = "Количество записей на странице.", example = "3")
            @RequestParam
            Integer size
    );

    @Operation(method = "GET", summary = "Список задач по автору.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список сформирован.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TasksListResponse.class)
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
                    description = "Пользователь не найдена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @SecurityRequirement(name = "JWT")
    ResponseEntity<Page<TasksListResponse>> getTaskListByCreator(
            @Parameter(description = "Id пользователя.", example = "1")
            @RequestParam
            Long userId,
            @Parameter(description = "Страница.", example = "0")
            @RequestParam
            Integer page,
            @Parameter(description = "Количество записей на странице.", example = "3")
            @RequestParam
            Integer size
    );

    @Operation(method = "GET", summary = "Список задач по исполнителю.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список сформирован.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TasksListResponse.class)
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
                    description = "Пользователь не найдена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @SecurityRequirement(name = "JWT")
    ResponseEntity<Page<TasksListResponse>> getTaskListByExecutor(
            @Parameter(description = "Id пользователя.", example = "1")
            @RequestParam
            Long userId,
            @Parameter(description = "Страница.", example = "0")
            @RequestParam
            Integer page,
            @Parameter(description = "Количество записей на странице.", example = "3")
            @RequestParam
            Integer size
    );
}