package ru.otus.task.tracker.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "Задача")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class TaskDto {
    @Schema(
            description = "Идентификатор задачи",
            example = "bde76ffa-f133-4c23-9bca-03618b2a94b2",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 36,
            maxLength = 36
    )
    private String id;

    @Schema(
            description = "Название задачи",
            example = "Разработать сервис",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 50
    )
    private String title;

    @Schema(
            description = "Описание задачи",
            example = "Необходимо разработать сервис для ...",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 1024
    )
    private String description;

    @Schema(
            description = "Идентификатор исполнителя",
            example = "001",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 5
    )
    private String assignee;

    @Schema(
            description = "Статус задачи",
            example = "TO DO",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 50
    )
    private String status;

    @Schema(
            description = "Приоритет задачи",
            example = "Высокий",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 20
    )
    private String priority;

    @Schema(
            description = "Срок выполнения",
            example = "2025-03-19T15:17:37",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LocalDateTime dueDate;
}