package dev.khrapatiy.taskmanagementsystem.service.impl;

import dev.khrapatiy.taskmanagementsystem.dto.request.ChangePriorityRequest;
import dev.khrapatiy.taskmanagementsystem.dto.request.ChangeStatusRequest;
import dev.khrapatiy.taskmanagementsystem.dto.request.CreateTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.request.EditTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.TaskResponse;
import dev.khrapatiy.taskmanagementsystem.entity.Task;
import dev.khrapatiy.taskmanagementsystem.entity.User;
import dev.khrapatiy.taskmanagementsystem.enums.Priority;
import dev.khrapatiy.taskmanagementsystem.enums.Status;
import dev.khrapatiy.taskmanagementsystem.exception.TaskNotFoundException;
import dev.khrapatiy.taskmanagementsystem.exception.UserNotFoundException;
import dev.khrapatiy.taskmanagementsystem.mapper.TaskMapper;
import dev.khrapatiy.taskmanagementsystem.repository.TaskRepository;
import dev.khrapatiy.taskmanagementsystem.repository.UserRepository;
import dev.khrapatiy.taskmanagementsystem.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public TaskResponse createTask(CreateTaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    public TaskResponse showTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Задача " + id + " не найдена."));
        return taskMapper.toDto(task);
    }

    @Override
    public TaskResponse updateTask(Long id, EditTaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Задача " + id + " не найдена."));
        Task editedTask = taskMapper.partialUpdate(task, taskDto);
        return taskMapper.toDto(editedTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Задача " + id + " не найдена."));
        taskRepository.deleteById(id);
    }

    @Override
    public TaskResponse changeStatus(Long taskId, ChangeStatusRequest request) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Задача " + taskId + " не найдена."));
        task.setStatus(Status.valueOf(request.status()));
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskResponse changePriority(Long taskId, ChangePriorityRequest request) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Задача " + taskId + " не найдена."));
        task.setPriority(Priority.valueOf(request.priority()));
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskResponse setExecutor(Long taskId, Long executorId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Задача " + taskId + " не найдена."));
        User user = userRepository.findById(executorId).orElseThrow(() -> new UserNotFoundException("Пользователь не найден."));
        task.setExecutor(user);
        return taskMapper.toDto(taskRepository.save(task));
    }
}