package ru.otus.task.tracker.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "Запрос на создание/активацию пользователя")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreateUserDtoRq {
    @Schema(
            description = "Имя пользователя",
            type = "string",
            example = "Nick",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 50
    )
    private String name;

    @Schema(
            description = "Логин пользователя",
            type = "string",
            example = "nickname",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 50
    )
    private String login;

    @Schema(
            description = "Роль пользователя",
            type = "string",
            example = "admin",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 50
    )
    private String role;
}