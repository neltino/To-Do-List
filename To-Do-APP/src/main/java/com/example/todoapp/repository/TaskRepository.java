package com.example.todoapp.repository;

import com.example.todoapp.enums.Status;
import com.example.todoapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findTaskByIdAndUserId (Long id, Long userId);
    List<Task> findAllByUserId(Long userId);
    List<Task> findByIdAndUserId (Long id, Long userId);
    List<Task>findAllByStatusAndUserId(Status status, Long userId);
}
