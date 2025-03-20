package ru.otus.task.tracker.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "Запрос на удаление пользователя")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class DeleteUserDtoRq {
    @Schema(
            description = "Логин пользователя",
            type = "string",
            example = "nickname",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 50
    )
    private String login;
}