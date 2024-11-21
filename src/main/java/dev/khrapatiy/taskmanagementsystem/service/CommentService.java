package dev.khrapatiy.taskmanagementsystem.service;

import dev.khrapatiy.taskmanagementsystem.dto.request.CreateCommentDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.CommentResponse;

public interface CommentService {
    CommentResponse createComment(Long taskId, CreateCommentDto commentDto);
}