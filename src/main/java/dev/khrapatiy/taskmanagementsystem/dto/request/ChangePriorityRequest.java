package dev.khrapatiy.taskmanagementsystem.dto.request;

import dev.khrapatiy.taskmanagementsystem.enums.Priority;
import dev.khrapatiy.taskmanagementsystem.utils.validate.ValueOfEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ChangePriorityRequest(
        @NotNull(message = "Не указано новое значение приоритета.")
        @NotEmpty(message = "Не указано новое значение приоритета.")
        @ValueOfEnum(
                enumClass = Priority.class,
                message = "Новый приоритет указан не верно. Укажите в качестве приоритета одно этих значений: LOW, NORMAL, HIGH."
        ) String priority
) {
}