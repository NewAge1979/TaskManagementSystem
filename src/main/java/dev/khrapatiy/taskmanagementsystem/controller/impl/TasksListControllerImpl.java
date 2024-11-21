package dev.khrapatiy.taskmanagementsystem.controller.impl;

import dev.khrapatiy.taskmanagementsystem.controller.TasksListController;
import dev.khrapatiy.taskmanagementsystem.dto.response.TasksListResponse;
import dev.khrapatiy.taskmanagementsystem.service.TasksListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/taskslist")
@RequiredArgsConstructor
@Log4j2
public class TasksListControllerImpl implements TasksListController {
    private final TasksListService tasksListService;

    @Override
    @GetMapping("")
    public ResponseEntity<Page<TasksListResponse>> getTaskList(@RequestParam Integer page, @RequestParam Integer size) {
        log.info("getTaskList");
        return ResponseEntity.status(200).body(tasksListService.getTasksList(PageRequest.of(page, size)));
    }

    @Override
    @GetMapping("/creator")
    public ResponseEntity<Page<TasksListResponse>> getTaskListByCreator(@RequestParam Long userId, @RequestParam Integer page, @RequestParam Integer size) {
        log.info("getTaskListByCreator");
        return ResponseEntity.status(200).body(tasksListService.getTaskListByCreator(userId, PageRequest.of(page, size)));
    }

    @Override
    @GetMapping("/executor")
    public ResponseEntity<Page<TasksListResponse>> getTaskListByExecutor(@RequestParam Long userId, @RequestParam Integer page, @RequestParam Integer size) {
        log.info("getTaskListByExecutor");
        return ResponseEntity.status(200).body(tasksListService.getTaskListByExecutor(userId, PageRequest.of(page, size)));
    }
}