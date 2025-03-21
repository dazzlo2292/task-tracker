package ru.otus.task.tracker.exceptions_handling;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationFieldErrorDto {
    private String field;
    private String message;

    public ValidationFieldErrorDto() {
    }

    public ValidationFieldErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
