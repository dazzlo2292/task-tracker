package ru.otus.task.tracker.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.otus.task.tracker.entities.Task;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Task, String> {
    @Query("SELECT t FROM Task t WHERE t.assignee = :assignee")
    List<Task> findAllByAssignee(String assignee, Pageable page);
}
