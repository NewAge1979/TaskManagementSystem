package dev.khrapatiy.taskmanagementsystem.service;

import dev.khrapatiy.taskmanagementsystem.dto.request.CreateTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.request.EditTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.TaskResponse;

public interface TaskService {
    TaskResponse createTask(CreateTaskDto taskDto);

    TaskResponse showTask(Long id);

    TaskResponse updateTask(Long id, EditTaskDto taskDto);

    void deleteTask(Long id);
}