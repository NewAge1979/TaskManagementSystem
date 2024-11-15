package dev.khrapatiy.taskmanagementsystem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Сообщение об ошибки.", requiredProperties = {"errorMessage"})
@Data
public class ErrorResponse {
    @Schema(description = "Текст сообщения.", example = "E-mail имеет не корректный формат: example@domain.", accessMode = Schema.AccessMode.AUTO)
    private String errorMessage;
}