package ru.otus.task.tracker.exceptions_handling;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationFieldError {
    private String field;
    private String message;

    public ValidationFieldError() {
    }

    public ValidationFieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
