package ru.otus.task.tracker.services;

import ru.otus.task.tracker.dtos.requests.ChangeTaskStatusDtoRq;
import ru.otus.task.tracker.dtos.requests.CreateTaskDtoRq;
import ru.otus.task.tracker.entities.Task;

import java.util.List;
import java.util.Optional;

public interface TasksService {
    Optional<Task> getTaskById(String id);
    List<Task> getAllTasksByAssignee(String assignee, Integer pageNumber, Integer pageSize);
    void createTask(String adminLogin, CreateTaskDtoRq createTaskDtoRq);
    void changeTaskStatus(ChangeTaskStatusDtoRq changeTaskStatusDtoRq);
}
