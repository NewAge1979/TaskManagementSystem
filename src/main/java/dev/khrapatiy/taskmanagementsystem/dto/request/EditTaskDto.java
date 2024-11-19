package dev.khrapatiy.taskmanagementsystem.dto.request;

import dev.khrapatiy.taskmanagementsystem.enums.Priority;
import dev.khrapatiy.taskmanagementsystem.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Данные для редактирования задачи.")
@Data
public class EditTaskDto {
    @Schema(description = "Заголовок задачи.", example = "Заголовок задачи.", accessMode = Schema.AccessMode.AUTO)
    @Size(message = "Заголовок задачи может содержать не более 255 символов.", max = 255)
    String title;

    @Schema(description = "Описание задачи.", example = "Описание задачи.", accessMode = Schema.AccessMode.AUTO)
    @Size(message = "Описание задач может содержать не более 1024 символа.", max = 1024)
    String description;

    @Schema(description = "Статус задачи.", example = "NEW", accessMode = Schema.AccessMode.AUTO)
    Status status;

    @Schema(description = "Приоритет задачи.", example = "LOW", accessMode = Schema.AccessMode.AUTO)
    Priority priority;

    @Schema(description = "Id создателя задачи.", example = "1", accessMode = Schema.AccessMode.AUTO)
    Long creatorId;

    @Schema(description = "Id исполнителя по задачи", example = "2", accessMode = Schema.AccessMode.AUTO)
    Long executorId;
}