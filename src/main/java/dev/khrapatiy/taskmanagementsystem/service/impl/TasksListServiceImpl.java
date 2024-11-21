package dev.khrapatiy.taskmanagementsystem.service.impl;

import dev.khrapatiy.taskmanagementsystem.dto.response.TasksListResponse;
import dev.khrapatiy.taskmanagementsystem.exception.UserNotFoundException;
import dev.khrapatiy.taskmanagementsystem.mapper.TasksListMapper;
import dev.khrapatiy.taskmanagementsystem.repository.TaskRepository;
import dev.khrapatiy.taskmanagementsystem.repository.UserRepository;
import dev.khrapatiy.taskmanagementsystem.service.TasksListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TasksListServiceImpl implements TasksListService {
    private final TaskRepository taskRepository;
    private final TasksListMapper tasksListMapper;
    private final UserRepository userRepository;

    @Override
    public Page<TasksListResponse> getTasksList(Pageable pageable) {
        return tasksListMapper.toDto2(taskRepository.findAll(pageable));
    }

    @Override
    public Page<TasksListResponse> getTaskListByCreator(Long userId, Pageable pageable) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Пользователь " + userId + " не найден."));
        return tasksListMapper.toDto2(taskRepository.findByCreatorId(userId, pageable));
    }

    @Override
    public Page<TasksListResponse> getTaskListByExecutor(Long userId, Pageable pageable) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Пользователь " + userId + " не найден."));
        return tasksListMapper.toDto2(taskRepository.findByExecutorId(userId, pageable));
    }
}