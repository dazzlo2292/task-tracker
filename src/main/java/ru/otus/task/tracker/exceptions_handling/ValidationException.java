package ru.otus.task.tracker.exceptions_handling;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private final String code;
    private final List<ValidationFieldError> errors;

    public ValidationException(String code, String message, List<ValidationFieldError> errors) {
        super(message);
        this.code = code;
        this.errors = errors;
    }
}
