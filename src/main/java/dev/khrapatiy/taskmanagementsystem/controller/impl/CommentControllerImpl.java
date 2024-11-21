package dev.khrapatiy.taskmanagementsystem.controller.impl;

import dev.khrapatiy.taskmanagementsystem.controller.CommentController;
import dev.khrapatiy.taskmanagementsystem.dto.request.CreateCommentDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.CommentResponse;
import dev.khrapatiy.taskmanagementsystem.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/comment")
@RequiredArgsConstructor
@Log4j2
public class CommentControllerImpl implements CommentController {
    private final CommentService commentService;

    @Override
    @PostMapping("/{taskId}")
    @PreAuthorize("@TaskExecutorService.isExecutorForTask(#taskId) && hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long taskId, @Valid @RequestBody CreateCommentDto commentDto) {
        log.info("Creating a new comment for task with id {}", taskId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(taskId, commentDto));
    }
}