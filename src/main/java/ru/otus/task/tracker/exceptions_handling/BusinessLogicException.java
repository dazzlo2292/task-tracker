package ru.otus.task.tracker.exceptions_handling;

public class BusinessLogicException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public BusinessLogicException(String message, String code) {
        super(message);
        this.code = code;
    }
}
