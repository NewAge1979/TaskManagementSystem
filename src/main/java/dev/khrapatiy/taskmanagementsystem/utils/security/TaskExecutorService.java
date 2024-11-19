package dev.khrapatiy.taskmanagementsystem.utils.security;

import dev.khrapatiy.taskmanagementsystem.entity.Task;
import dev.khrapatiy.taskmanagementsystem.entity.User;
import dev.khrapatiy.taskmanagementsystem.exception.TaskNotFoundException;
import dev.khrapatiy.taskmanagementsystem.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("TaskExecutorService")
@RequiredArgsConstructor
public class TaskExecutorService {
    private final TaskRepository taskRepository;

    @Transactional
    public boolean isExecutorForTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Задача " + taskId + "  не найдена."));
        Long executorId = task.getExecutor().getId();
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return userId.equals(executorId);
    }
}