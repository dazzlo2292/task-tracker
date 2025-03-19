package ru.otus.task.tracker.dtos;

import java.util.List;

public record TasksPageDto(List<TaskDto> entries) {
}