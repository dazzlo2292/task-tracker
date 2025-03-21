package ru.otus.task.tracker.exceptions_handling;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "Ошибка")
@Getter
@Setter
public class ErrorDto {
    @Schema(
            description = "Код ошибки",
            type = "string",
            example = "INTERNAL_SERVER_ERROR",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String code;

    @Schema(
            description = "Сообщение с подробностями об ошибке",
            type = "string",
            example = "Произошла непредвиденная ошибка",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String message;

    @Schema(
            description = "Время возникновения ошибки",
            type = "dateTime",
            example = "2025-02-12T18:04:44.049",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LocalDateTime dateTime;

    public ErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }
}
