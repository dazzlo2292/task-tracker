package ru.otus.task.tracker.exceptions_handling;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ValidationErrorDto {
    private String code;
    private String message;
    private List<ValidationFieldErrorDto> errors;
    private LocalDateTime dateTime;

    public ValidationErrorDto(String code, String message, List<ValidationFieldErrorDto> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
        this.dateTime = LocalDateTime.now();
    }
}
