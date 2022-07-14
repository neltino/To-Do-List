package com.example.todoapp.service;

import com.example.todoapp.dto.TaskDto;
import com.example.todoapp.enums.Status;
import com.example.todoapp.model.Task;

import java.util.List;


public interface TaskService {

    String createTask(TaskDto taskDto);
    String updateTaskTitle(Long id, String title);
    String updateTaskDesc(Long id, String title);
    String updateTask(Long id, String title, String desc);
    String ToCompleted(Long id);
    String ToInProgress(Long id);
    String ToPending(Long id);
    String deleteTask(Long id);
    List<Task> viewAllTask(Long userId);
    List<Task>findTaskById(Long id, Long userId);
    List<Task>findTaskByStatus(Status status, Long userId);

}
