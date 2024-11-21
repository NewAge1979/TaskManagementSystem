package dev.khrapatiy.taskmanagementsystem.controller.impl;

import dev.khrapatiy.taskmanagementsystem.controller.TaskController;
import dev.khrapatiy.taskmanagementsystem.dto.request.CreateTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.request.EditTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.TaskResponse;
import dev.khrapatiy.taskmanagementsystem.enums.Priority;
import dev.khrapatiy.taskmanagementsystem.enums.Status;
import dev.khrapatiy.taskmanagementsystem.service.TaskService;
import dev.khrapatiy.taskmanagementsystem.utils.validate.ValueOfEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/task")
@RequiredArgsConstructor
@Log4j2
public class TaskControllerImpl implements TaskController {
    private final TaskService taskService;

    @Override
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskDto taskDto) {
        log.info("Creating a new task");
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(taskDto));
    }

    @Override
    @GetMapping("/{taskId}")
    @PreAuthorize("@TaskExecutorService.isExecutorForTask(#taskId) && hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> showTask(@PathVariable Long taskId) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.showTask(taskId));
    }

    @Override
    @PatchMapping("/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long taskId, @Valid @RequestBody EditTaskDto taskDto) {
        log.info("Updating a task");
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(taskId, taskDto));
    }

    @Override
    @DeleteMapping("/deleteTask/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        log.info("Deleting a task");
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }

    @Override
    @PatchMapping("/changeStatus")
    @PreAuthorize("@TaskExecutorService.isExecutorForTask(#taskId) && hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> changeStatus(
            @RequestParam
            @Valid
            @NotNull(message = "Не указан id задачи.")
            Long taskId,
            @RequestParam
            @Valid
            @NotNull(message = "Не указано новое значение статуса.")
            @ValueOfEnum(
                    enumClass = Status.class,
                    message = "Новый статус указан не верно. Укажите в качестве статуса одно этих значений: NEW, FINISHED, AWAITING, RUNNING."
            )
            String status
    ) {
        log.info("Changing status of a task");
        return ResponseEntity.status(HttpStatus.OK).body(taskService.changeStatus(taskId, status));
    }

    @Override
    @PatchMapping("/changePriority")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> changePriority(
            @RequestParam
            @Valid
            @NotNull(message = "Не указан id задачи.")
            Long taskId,
            @RequestParam
            @Valid
            @NotNull(message = "Не указано новое значение приоритета.")
            @ValueOfEnum(
                    enumClass = Priority.class,
                    message = "Новый приоритет указан не верно. Укажите в качестве приоритета одно этих значений: LOW, NORMAL, HIGH."
            )
            String priority
    ) {
        log.info("Changing priority of a task");
        return ResponseEntity.status(HttpStatus.OK).body(taskService.changePriority(taskId, priority));
    }

    @Override
    @PatchMapping("/setExecutor")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> setExecutor(
            @RequestParam
            @Valid
            @NotNull(message = "Не указан id задачи.")
            Long taskId,
            @RequestParam
            @Valid
            @NotNull(message = "Не указан id исполнителя.")
            Long executorId
    ) {
        log.info("Setting executor of a task");
        return ResponseEntity.status(HttpStatus.OK).body(taskService.setExecutor(taskId, executorId));
    }
}