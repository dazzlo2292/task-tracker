package ru.otus.task.tracker.services;

import ru.otus.task.tracker.dtos.requests.CreateUserDtoRq;
import ru.otus.task.tracker.dtos.requests.DeleteUserDtoRq;
import ru.otus.task.tracker.entities.User;

import java.util.Optional;

public interface UsersService {
    Optional<User> getUserByLogin(String login);
    void createUser(String adminLogin, CreateUserDtoRq createUserDtoRq);
    void deleteUser(String adminLogin, DeleteUserDtoRq deleteUserDtoRq);
}
