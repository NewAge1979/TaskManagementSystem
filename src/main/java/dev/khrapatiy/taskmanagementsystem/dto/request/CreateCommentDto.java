package dev.khrapatiy.taskmanagementsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Данные для создания комментария.", requiredProperties = {"text"})
@Data
public class CreateCommentDto {
    @Schema(description = "Текст комментария.", example = "Какой-то комментарий.", accessMode = Schema.AccessMode.AUTO)
    @NotNull(message = "Необходимо ввести комментарий.")
    @Size(message = "Комментарий может содержать не более 1024 символа.", max = 1024)
    @NotEmpty(message = "Необходимо ввести комментарий.")
    String text;
}