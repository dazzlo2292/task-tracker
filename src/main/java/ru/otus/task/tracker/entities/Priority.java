package ru.otus.task.tracker.entities;

import lombok.Getter;

@Getter
public enum Priority {
    CRITICAL(0, "Критический"),
    HIGH(1, "Высокий"),
    MEDIUM(2, "Средний"),
    LOW(3, "Низкий");

    private final int number;
    private final String description;

    Priority(int number, String description) {
        this.number = number;
        this.description = description;
    }
}
