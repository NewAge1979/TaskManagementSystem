package dev.khrapatiy.taskmanagementsystem.service;

import dev.khrapatiy.taskmanagementsystem.dto.response.TasksListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TasksListService {
    Page<TasksListResponse> getTasksList(Pageable pageable);

    Page<TasksListResponse> getTaskListByCreator(Long userId, Pageable pageable);

    Page<TasksListResponse> getTaskListByExecutor(Long userId, Pageable pageable);
}