package dev.khrapatiy.taskmanagementsystem.dto.request;

import dev.khrapatiy.taskmanagementsystem.enums.Status;
import dev.khrapatiy.taskmanagementsystem.utils.validate.ValueOfEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ChangeStatusRequest(
        @NotNull(message = "Не указано новое значение статуса.")
        @NotEmpty(message = "Не указано новое значение статуса.")
        @ValueOfEnum(
                enumClass = Status.class,
                message = "Новый статус указан не верно. Укажите в качестве статуса одно этих значений: NEW, FINISHED, AWAITING, RUNNING."
        ) String status
) {
}