package ru.otus.task.tracker.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Schema(description = "Запрос на создание/активацию пользователя")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreateTaskDtoRq {
    @Schema(
            description = "Название задачи",
            type = "string",
            example = "Разработка модуля",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 50
    )
    private String title;

    @Schema(
            description = "Описание задачи",
            type = "string",
            example = "Необходимо разработать модуль для ...",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 1024
    )
    private String description;

    @Schema(
            description = "Логин исполнителя задачи",
            type = "string",
            example = "nickname",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 50
    )
    private String assignee;

    @Schema(
            description = "Приоритет задачи",
            type = "integer",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "0",
            maximum = "3",
            defaultValue = "3"
    )
    private int priority;

    @Schema(
            description = "Срок выполнения задачи",
            type = "LocalDateTime",
            example = "2025-03-19T15:17:37",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LocalDateTime dueDate;
}
