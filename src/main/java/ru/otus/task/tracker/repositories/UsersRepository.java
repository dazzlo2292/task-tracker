package ru.otus.task.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.task.tracker.entities.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {
    Optional<User> findByLogin(String login);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.blockFlag = 'Y' WHERE login = :login")
    void blockByLogin(String login);

}
