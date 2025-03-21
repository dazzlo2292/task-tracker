package ru.otus.task.tracker.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "Запрос на изменение статуса задачи")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ChangeTaskStatusDtoRq {
    @Schema(
            description = "Идентификатор задачи",
            type = "string",
            example = "1937a625-d6de-4228-b686-5706020a907e",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 36,
            maxLength = 36
    )
    private String id;

    @Schema(
            description = "Статус задачи (TO_DO / IN_PROGRESS / DONE)",
            type = "string",
            example = "IN_PROGRESS",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 50
    )
    private String status;
}