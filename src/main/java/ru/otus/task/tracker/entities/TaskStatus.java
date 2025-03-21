package ru.otus.task.tracker.entities;

import lombok.Getter;

@Getter
public enum TaskStatus {
    TO_DO("Назначена"),
    IN_PROGRESS("В работе"),
    DONE("Выполнена");

    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }
}
