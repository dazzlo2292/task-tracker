package ru.otus.task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.otus.task.tracker.dtos.requests.ChangeTaskStatusDtoRq;
import ru.otus.task.tracker.dtos.requests.CreateTaskDtoRq;
import ru.otus.task.tracker.entities.Priority;
import ru.otus.task.tracker.entities.Task;
import ru.otus.task.tracker.entities.TaskStatus;
import ru.otus.task.tracker.entities.User;
import ru.otus.task.tracker.repositories.TasksRepository;
import ru.otus.task.tracker.repositories.UsersRepository;
import ru.otus.task.tracker.validators.TaskRequestValidator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {
    private final UsersRepository usersRepository;
    private final TasksRepository tasksRepository;
    private final TaskRequestValidator taskRequestValidator;

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

    @Override
    public void createTask(String adminLogin, CreateTaskDtoRq createTaskDtoRq) {
        try {
            taskRequestValidator.validateCreateTaskRequest(createTaskDtoRq);
            taskRequestValidator.validateCreateTaskParameters(adminLogin, createTaskDtoRq);

            Optional<User> assignee = usersRepository.findByLogin(createTaskDtoRq.getAssignee());

            String priority = setPriority(createTaskDtoRq.getPriority());

            Task newTask = new Task(
                    UUID.randomUUID().toString(),
                    createTaskDtoRq.getTitle(),
                    createTaskDtoRq.getDescription(),
                    assignee.get().getId(),
                    TaskStatus.TO_DO.getDescription(),
                    priority,
                    createTaskDtoRq.getDueDate()
            );

            tasksRepository.save(newTask);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void changeTaskStatus(ChangeTaskStatusDtoRq changeTaskStatusDtoRq) {
        try {
            taskRequestValidator.validateChangeTaskStatusRequest(changeTaskStatusDtoRq);
            taskRequestValidator.validateChangeTaskStatusParameters(changeTaskStatusDtoRq);

            Optional<Task> task = tasksRepository.findById(changeTaskStatusDtoRq.getId());

            task.get().setStatus(TaskStatus.valueOf(changeTaskStatusDtoRq.getStatus()).getDescription());
            tasksRepository.save(task.get());
        } catch (Exception e) {
            throw e;
        }
    }

    private String setPriority(int currentPriority) {
        int priority = currentPriority;
        String priorityDescription = "";

        if (priority < 0) {
            priority = 0;
        }
        if (priority > 3) {
            priority = 3;
        }

        for (Priority p : Priority.values()) {
            if (p.getNumber() == priority) {
                priorityDescription = p.getDescription();
            }
        }
        return priorityDescription;
    }
}
