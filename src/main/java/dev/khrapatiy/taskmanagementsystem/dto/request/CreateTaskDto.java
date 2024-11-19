package dev.khrapatiy.taskmanagementsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Данные для создания задачи.", requiredProperties = {"title", "description"})
@Data
public class CreateTaskDto {
    @Schema(description = "Заголовок задачи.", example = "Заголовок задачи.", accessMode = Schema.AccessMode.AUTO)
    @NotNull(message = "Не указан заголовок задачи.")
    @Size(message = "Заголовок задачи может содержать не более 255 символов.", max = 255)
    @NotEmpty(message = "Не указан заголовок задачи.")
    String title;

    @Schema(description = "Описание задачи.", example = "Описание задачи.", accessMode = Schema.AccessMode.AUTO)
    @NotNull(message = "Не заполнено описание задачи.")
    @Size(message = "Описание задач может содержать не более 1024 символа.", max = 1024)
    @NotEmpty(message = "Не заполнено описание задачи.")
    String description;
}