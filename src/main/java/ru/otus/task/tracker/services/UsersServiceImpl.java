package ru.otus.task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.task.tracker.entities.User;
import ru.otus.task.tracker.repositories.UsersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Override
    public Optional<User> getUserByLogin(String login) {
        return usersRepository.findByLoginAndBlockFlag(login, 'N');
    }

}
