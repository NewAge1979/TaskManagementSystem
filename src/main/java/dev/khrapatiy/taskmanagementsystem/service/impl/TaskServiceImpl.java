package dev.khrapatiy.taskmanagementsystem.service.impl;

import dev.khrapatiy.taskmanagementsystem.dto.request.CreateTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.request.EditTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.TaskResponse;
import dev.khrapatiy.taskmanagementsystem.entity.Task;
import dev.khrapatiy.taskmanagementsystem.exception.TaskException;
import dev.khrapatiy.taskmanagementsystem.mapper.TaskMapper;
import dev.khrapatiy.taskmanagementsystem.repository.TaskRepository;
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

    @Override
    public TaskResponse createTask(CreateTaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    public TaskResponse showTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskException("Задача " + id + " не найдена."));
        return taskMapper.toDto(task);
    }

    @Override
    public TaskResponse updateTask(Long id, EditTaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskException("Задача " + id + " не найдена."));
        Task editedTask = taskMapper.partialUpdate(taskDto, task);
        return taskMapper.toDto(editedTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.findById(id).orElseThrow(() -> new TaskException("Задача " + id + " не найдена."));
        taskRepository.deleteById(id);
    }
}