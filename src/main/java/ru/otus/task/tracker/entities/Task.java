package ru.otus.task.tracker.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "assignee")
    private String assignee;

    @Column(name = "status")
    private String status;

    @Column(name = "priority")
    private String priority;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Task(String id, String title, String description, String assignee, String status, String priority, LocalDateTime dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }
}
