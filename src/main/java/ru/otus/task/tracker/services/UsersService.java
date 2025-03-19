package ru.otus.task.tracker.services;

import ru.otus.task.tracker.entities.User;

import java.util.Optional;

public interface UsersService {
    Optional<User> getUserByLogin(String login);
}
