package ru.otus.task.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.task.tracker.entities.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {
    Optional<User> findByLogin(String login);
}
