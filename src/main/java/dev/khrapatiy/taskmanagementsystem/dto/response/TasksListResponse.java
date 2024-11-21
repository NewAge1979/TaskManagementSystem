package dev.khrapatiy.taskmanagementsystem.dto.response;

import dev.khrapatiy.taskmanagementsystem.enums.Priority;
import dev.khrapatiy.taskmanagementsystem.enums.Status;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.time.OffsetDateTime;
import java.util.List;

@Value
public class TasksListResponse {
    Long id;
    @Size(max = 255)
    String title;
    @Size(max = 1024)
    String description;
    Status status;
    Priority priority;
    TaskCreatorDto creator;
    TaskExecutorDto executor;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
    List<TaskCommentsDto> comments;

    @Value
    public static class TaskCreatorDto {
        Long id;
        String email;
    }

    @Value
    public static class TaskExecutorDto {
        Long id;
        String email;
    }

    @Value
    public static class TaskCommentsDto {
        Long id;
        @Size(max = 1024)
        String text;
        CommentCreatorDto creator;
        OffsetDateTime createAt;

        @Value
        public static class CommentCreatorDto {
            Long id;
            String email;
        }
    }
}