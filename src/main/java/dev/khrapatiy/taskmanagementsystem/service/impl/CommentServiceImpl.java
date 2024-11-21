package dev.khrapatiy.taskmanagementsystem.service.impl;

import dev.khrapatiy.taskmanagementsystem.dto.request.CreateCommentDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.CommentResponse;
import dev.khrapatiy.taskmanagementsystem.entity.Comment;
import dev.khrapatiy.taskmanagementsystem.entity.Task;
import dev.khrapatiy.taskmanagementsystem.exception.TaskNotFoundException;
import dev.khrapatiy.taskmanagementsystem.mapper.CommentMapper;
import dev.khrapatiy.taskmanagementsystem.repository.CommentRepository;
import dev.khrapatiy.taskmanagementsystem.repository.TaskRepository;
import dev.khrapatiy.taskmanagementsystem.service.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final TaskRepository taskRepository;

    @Override
    public CommentResponse createComment(Long taskId, CreateCommentDto commentDto) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Задача " + taskId + " не найдена."));
        Comment comment = commentMapper.toEntity(commentDto);
        comment.setTask(task);
        return commentMapper.toDto(commentRepository.save(comment));
    }
}