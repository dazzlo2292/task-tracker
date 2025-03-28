package ru.otus.task.tracker.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.task.tracker.dtos.requests.CreateUserDtoRq;
import ru.otus.task.tracker.dtos.requests.DeleteUserDtoRq;
import ru.otus.task.tracker.entities.User;
import ru.otus.task.tracker.exceptions_handling.BusinessLogicException;
import ru.otus.task.tracker.exceptions_handling.ResourceNotFoundException;
import ru.otus.task.tracker.exceptions_handling.ValidationException;
import ru.otus.task.tracker.exceptions_handling.ValidationFieldError;
import ru.otus.task.tracker.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRequestValidator {
    private final UsersRepository usersRepository;

    public void validateCreateUserRequest(CreateUserDtoRq createUserDtoRq) {
        List<ValidationFieldError> errors = new ArrayList<>();
        if (createUserDtoRq.getName().length() > 50) {
            errors.add(new ValidationFieldError("name", "Длина имени не должна превышать 50 символов"));
        }
        if (createUserDtoRq.getLogin().length() > 50) {
            errors.add(new ValidationFieldError("login", "Длина логина не должна превышать 50 символов"));
        }
        if (!errors.isEmpty()) {
            throw new ValidationException("CREATE_USER_VALIDATION_ERROR", "Проблемы заполнения полей пользователя", errors);
        }
    }

    public void validateCreateUserParameters(String adminLogin, CreateUserDtoRq createUserDtoRq) {
        Optional<User> admin = usersRepository.findByLogin(adminLogin);
        Optional<User> user = usersRepository.findByLogin(createUserDtoRq.getLogin());

        if (admin.isEmpty()) {
            throw new ResourceNotFoundException("Администратор с указанным логином не найден");
        } else if (!admin.get().getRole().equals("admin")) {
            throw new BusinessLogicException("PERMISSION_DENIED","Создание пользователей запрещено для указанного пользователя");
        }
        if (user.isPresent() && user.get().getBlockFlag() == 'N') {
            throw new BusinessLogicException("INCORRECT_USER_LOGIN","Пользователь с указанным логином уже существует");
        }
    }

    public void validateDeleteUserRequest(DeleteUserDtoRq deleteUserDtoRq) {
        List<ValidationFieldError> errors = new ArrayList<>();

        if (deleteUserDtoRq.getLogin().length() > 50) {
            errors.add(new ValidationFieldError("login", "Длина логина не должна превышать 50 символов"));
        }
        if (!errors.isEmpty()) {
            throw new ValidationException("DELETE_USER_VALIDATION_ERROR", "Проблемы заполнения полей пользователя", errors);
        }
    }

    public void validateDeleteUserParameters(String adminLogin, DeleteUserDtoRq deleteUserDtoRq) {
        Optional<User> admin = usersRepository.findByLogin(adminLogin);
        Optional<User> user = usersRepository.findByLogin(deleteUserDtoRq.getLogin());

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Пользователь с указанным логином не найден");
        }
        if (admin.isEmpty()) {
            throw new ResourceNotFoundException("Администратор с указанным логином не найден");
        } else if (!admin.get().getRole().equals("admin")) {
            throw new BusinessLogicException("PERMISSION_DENIED","Удаление пользователей запрещено для указанного пользователя");
        }
    }
}
