package dev.khrapatiy.taskmanagementsystem.dto.response;

import dev.khrapatiy.taskmanagementsystem.enums.Priority;
import dev.khrapatiy.taskmanagementsystem.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.OffsetDateTime;

@Schema(description = "Данные по задачи.")
@Data
public class TaskResponse {
    Long id;

    @Schema(description = "Заголовок задачи.", example = "Заголовок задачи.", accessMode = Schema.AccessMode.AUTO)
    String title;

    @Schema(description = "Описание задачи.", example = "Описание задачи.", accessMode = Schema.AccessMode.AUTO)
    String description;

    @Schema(description = "Статус задачи.", example = "NEW", accessMode = Schema.AccessMode.AUTO)
    Status status;

    @Schema(description = "Приоритет задачи.", example = "LOW", accessMode = Schema.AccessMode.AUTO)
    Priority priority;

    @Schema(description = "Создатель задачи.")
    UserCreatorDto creator;

    @Schema(description = "Исполнитель по задачи.")
    UserExecutorDto executor;

    @Schema(description = "Дата создания задачи.", example = "2024-11-19T03:41:21.323Z", accessMode = Schema.AccessMode.AUTO)
    OffsetDateTime createdAt;

    @Schema(description = "Дата редактирования задачи.", example = "2024-11-19T03:41:21.323Z", accessMode = Schema.AccessMode.AUTO)
    OffsetDateTime updatedAt;

    public record UserCreatorDto(
            @Schema(description = "Email создателя задачи.", example = "admin@email.com", accessMode = Schema.AccessMode.AUTO)
            String email
    ) {
    }

    public record UserExecutorDto(
            @Schema(description = "Email исполнителя по задачи", example = "user@email.com", accessMode = Schema.AccessMode.AUTO)
            String email
    ) {
    }
}