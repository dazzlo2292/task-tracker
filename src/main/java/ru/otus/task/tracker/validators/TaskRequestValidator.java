package ru.otus.task.tracker.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.task.tracker.dtos.requests.ChangeTaskStatusDtoRq;
import ru.otus.task.tracker.dtos.requests.CreateTaskDtoRq;
import ru.otus.task.tracker.entities.Task;
import ru.otus.task.tracker.entities.User;
import ru.otus.task.tracker.exceptions_handling.BusinessLogicException;
import ru.otus.task.tracker.exceptions_handling.ResourceNotFoundException;
import ru.otus.task.tracker.exceptions_handling.ValidationException;
import ru.otus.task.tracker.exceptions_handling.ValidationFieldError;
import ru.otus.task.tracker.repositories.TasksRepository;
import ru.otus.task.tracker.repositories.UsersRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskRequestValidator {
    private final TasksRepository tasksRepository;
    private final UsersRepository usersRepository;

    public void validateCreateTaskRequest(CreateTaskDtoRq createTaskDtoRq) {
        List<ValidationFieldError> errors = new ArrayList<>();
        if (createTaskDtoRq.getTitle().length() > 50) {
            errors.add(new ValidationFieldError("title", "Длина названия не должна превышать 50 символов"));
        }
        if (createTaskDtoRq.getDescription().length() > 1024) {
            errors.add(new ValidationFieldError("description", "Длина описания не должна превышать 1024 символа"));
        }
        if (!errors.isEmpty()) {
            throw new ValidationException("CREATE_TASK_VALIDATION_ERROR", "Проблемы заполнения полей пользователя", errors);
        }
    }

    public void validateCreateTaskParameters(String adminLogin, CreateTaskDtoRq createTaskDtoRq) {
        Optional<User> admin = usersRepository.findByLogin(adminLogin);
        Optional<User> assignee = usersRepository.findByLogin(createTaskDtoRq.getAssignee());

        if (admin.isEmpty()) {
            throw new ResourceNotFoundException("Администратор с указанным логином не найден");
        } else if (!admin.get().getRole().equals("admin")) {
            throw new BusinessLogicException("PERMISSION_DENIED","Создание задач запрещено для указанного пользователя");
        }
        if (assignee.isEmpty() || assignee.get().getBlockFlag() == 'Y') {
            throw new ResourceNotFoundException("Пользователь с указанным логином не найден");
        }
        if (createTaskDtoRq.getDueDate().isBefore(LocalDateTime.now())) {
            throw new BusinessLogicException("INCORRECT_DUE_DATE","Срок исполнения не может быть раньше текущего времени");
        }
    }

    public void validateChangeTaskStatusRequest(ChangeTaskStatusDtoRq changeTaskStatusDtoRq) {
        List<ValidationFieldError> errors = new ArrayList<>();
        if (changeTaskStatusDtoRq.getId().length() != 36) {
            errors.add(new ValidationFieldError("id", "Длина ID должна быть 36 символов"));
        }
        if (!errors.isEmpty()) {
            throw new ValidationException("CHANGE_TASK_STATUS_VALIDATION_ERROR", "Проблемы заполнения полей пользователя", errors);
        }
    }

    public void validateChangeTaskStatusParameters(ChangeTaskStatusDtoRq changeTaskStatusDtoRq) {
        Optional<Task> task = tasksRepository.findById(changeTaskStatusDtoRq.getId());

        if (task.isEmpty()) {
            throw new ResourceNotFoundException("Задача с указанным ID не найдена");
        }
    }
}
