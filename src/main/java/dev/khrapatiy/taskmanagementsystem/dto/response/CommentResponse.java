package dev.khrapatiy.taskmanagementsystem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.OffsetDateTime;

@Schema(description = "Данные по комментарию.")
@Data
public class CommentResponse {
    Long id;

    @Schema(description = "Текст комментария.", example = "Тут что-то пишем.", accessMode = Schema.AccessMode.AUTO)
    @Size(max = 1024)
    String text;

    @Schema(description = "Автор комментария.", accessMode = Schema.AccessMode.AUTO)
    UserCommentDto creator;

    @Schema(description = "Дата добавления.", accessMode = Schema.AccessMode.AUTO)
    OffsetDateTime createAt;

    public record UserCommentDto(
            @Schema(description = "Автор комментария.", example = "admin@email.com", accessMode = Schema.AccessMode.AUTO)
            String email
    ) {
    }
}