package ru.otus.task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.otus.task.tracker.entities.Task;
import ru.otus.task.tracker.repositories.TasksRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {
    private final TasksRepository tasksRepository;

    private static final int MAX_PAGE_SIZE = 100;

    @Override
    public Optional<Task> getTaskById(String id) {
        return tasksRepository.findById(id);
    }

    @Override
    public List<Task> getAllTasksByAssignee(String assignee, Integer pageNumber, Integer pageSize) {
        if (pageSize > MAX_PAGE_SIZE) {
            pageSize = MAX_PAGE_SIZE;
        }
        return tasksRepository.findAllByAssignee(assignee, PageRequest.of(pageNumber, pageSize));
    }
}
