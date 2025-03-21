package ru.otus.task.tracker.dtos;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Пользователь")
public record UserDto(
        @Schema(
                description = "Идентификатор пользователя",
                type = "string",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 5
        )
        String id,

        @Schema(
                description = "Имя пользователя",
                type = "string",
                example = "Nick",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 50
        )
        String name,

        @Schema(
                description = "Логин пользователя",
                type = "string",
                example = "nick_admin",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 50
        )
        String login,

        @Schema(
                description = "Роль пользователя",
                type = "string",
                example = "admin",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 20
        )
        String role,

        @Schema(
                description = "Признак блокировки пользователя",
                type = "char",
                example = "N",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        char blockFlag
) {
}