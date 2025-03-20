package ru.otus.task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.task.tracker.dtos.requests.CreateUserDtoRq;
import ru.otus.task.tracker.dtos.requests.DeleteUserDtoRq;
import ru.otus.task.tracker.entities.User;
import ru.otus.task.tracker.repositories.UsersRepository;
import ru.otus.task.tracker.validators.UserRequestValidator;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final UserRequestValidator userRequestValidator;

    @Override
    public Optional<User> getUserByLogin(String login) {
        return usersRepository.findByLogin(login);
    }

    @Override
    public void createUser(CreateUserDtoRq createUserDtoRq) {
        try {
            userRequestValidator.validateCreateUserRequest(createUserDtoRq);
            userRequestValidator.validateCreateUserParameters(createUserDtoRq);

            Optional<User> currentUser = usersRepository.findByLogin(createUserDtoRq.getLogin());

            if (currentUser.isEmpty()) {
                User newUser = new User(
                        createUserDtoRq.getName(),
                        createUserDtoRq.getLogin(),
                        createUserDtoRq.getRole(),
                        'N'
                );
                usersRepository.save(newUser);
            } else {
                currentUser.get().setBlockFlag('N');
                usersRepository.save(currentUser.get());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteUser(DeleteUserDtoRq deleteUserDtoRq) {
        try {
            userRequestValidator.validateDeleteUserRequest(deleteUserDtoRq);
            userRequestValidator.validateDeleteUserParameters(deleteUserDtoRq);

            usersRepository.blockByLogin(deleteUserDtoRq.getLogin());
        } catch (Exception e) {
            throw e;
        }
    }
}
