package dev.khrapatiy.taskmanagementsystem.controller.impl;

import dev.khrapatiy.taskmanagementsystem.controller.TaskController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0/task")
@RequiredArgsConstructor
@Log4j2
public class TaskControllerImpl implements TaskController {
    @Override
    @PostMapping("/createTask")
    public ResponseEntity<?> createTask() {
        return null;
    }
}