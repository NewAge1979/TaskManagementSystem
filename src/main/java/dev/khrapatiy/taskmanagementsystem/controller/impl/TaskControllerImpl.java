package dev.khrapatiy.taskmanagementsystem.controller.impl;

import dev.khrapatiy.taskmanagementsystem.controller.TaskController;
import dev.khrapatiy.taskmanagementsystem.dto.request.CreateTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.request.EditTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.TaskResponse;
import dev.khrapatiy.taskmanagementsystem.service.TaskService;
import jakarta.validation.Valid;
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
    @PostMapping("/createTask")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskDto taskDto) {
        log.info("Creating a new task");
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(taskDto));
    }

    @Override
    @GetMapping("/showTask/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> showTask(@PathVariable(name = "taskId") Long taskId) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.showTask(taskId));
    }

    @Override
    @PatchMapping("/updateTask/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable(name = "taskId") Long taskId, @Valid @RequestBody EditTaskDto taskDto) {
        log.info("Updating a task");
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(taskId, taskDto));
    }

    @Override
    @DeleteMapping("/deleteTask/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "taskId") Long taskId) {
        log.info("Deleting a task");
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }
}