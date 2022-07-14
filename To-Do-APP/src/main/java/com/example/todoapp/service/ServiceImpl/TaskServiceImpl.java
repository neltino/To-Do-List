package com.example.todoapp.service.ServiceImpl;

import com.example.todoapp.CustomException.CustomException;
import com.example.todoapp.dto.TaskDto;
import com.example.todoapp.enums.Status;
import com.example.todoapp.model.Task;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {


    private final TaskRepository taskRepository;
    private final HttpSession httpSession;

    @Override
    public String createTask(TaskDto taskDto) {

        Task task = Task.builder()
                .title(taskDto.getTitle())
                .userId((Long) httpSession.getAttribute("user_id"))
                .description(taskDto.getDescription())
                .status(Status.PENDING)
                .createdAt(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()))
                .updatedAt(new SimpleDateFormat("0000-00-00 00:00").format(new Date()))
                .completedAt(new SimpleDateFormat("0000-00-00 00:00").format(new Date()))
                .build();
        taskRepository.save(task);
        return "Task Successfully Created";

    }
    @Override
    public String updateTaskTitle(Long id, String title){
        Task task = taskRepository.findTaskByIdAndUserId(id, (Long) httpSession.getAttribute("user_id"));
        if(task == null){
            throw new CustomException("Invalid task ID provided or Task ID belongs to another user!");
        }
        task.setTitle(title);
        task.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        taskRepository.save(task);
        return "Task  title updated successfully!";
    }

    @Override
    public String updateTaskDesc(Long id, String desc){
        Task task = taskRepository.findTaskByIdAndUserId(id, (Long) httpSession.getAttribute("user_id"));
        if(task == null){
            throw new CustomException("Invalid task ID provided or Task ID belongs to another user!");
        }
        task.setDescription(desc);
        task.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        taskRepository.save(task);
        return "Task  description updated successfully!";
    }
    @Override
    public String updateTask(Long id, String title, String desc){
        Task upTask = taskRepository.findTaskByIdAndUserId(id, (Long) httpSession.getAttribute("user_id"));
        if(upTask == null){
            throw new CustomException("Invalid Task Id provided!");
        }

        upTask.setTitle(title);
        upTask.setDescription(desc);
        upTask.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        taskRepository.save(upTask);
        return "Task updated successfully!";
    }
    @Override
    public String ToInProgress(Long id){
        Task upTask = taskRepository.findTaskByIdAndUserId(id, (Long) httpSession.getAttribute("user_id"));
        if(upTask == null){
            throw new CustomException("Invalid task ID provided or Task ID belongs to another user!");
        }

        upTask.setStatus(Status.IN_PROGRESS);
        upTask.setCompletedAt(new SimpleDateFormat("0000-00-00 00:00").format(new Date()));
        upTask.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));

        taskRepository.save(upTask);
        return "Task status updated to 'IN-PROGRESS' successfully!";
    }

    @Override
    public String ToCompleted(Long id){
        Task upTask = taskRepository.findTaskByIdAndUserId(id, (Long) httpSession.getAttribute("user_id"));
        if(upTask == null){
            throw new CustomException("Invalid task ID provided or Task ID belongs to another user!");
        }
        upTask.setStatus(Status.DONE);
        upTask.setCompletedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        taskRepository.save(upTask);
        return "Task status updated to 'DONE' successfully!";
    }

    @Override
    public String ToPending(Long id){
        Task upTask = taskRepository.findTaskByIdAndUserId(id, (Long) httpSession.getAttribute("user_id"));
        if(upTask == null){
            throw new CustomException("Invalid task ID provided or Task ID belongs to another user!");
        }
        upTask.setStatus(Status.PENDING);
        upTask.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        upTask.setCompletedAt(new SimpleDateFormat("0000-00-00 00:00").format(new Date()));
        taskRepository.save(upTask);
        return "Task status updated to 'PENDING' successfully!";
    }

    @Override
    public String deleteTask(Long id){
        Task delete = taskRepository.findTaskByIdAndUserId(id, (Long) httpSession.getAttribute("user_id"));
        if(delete == null){
            throw new CustomException("Invalid task ID provided or Task ID belongs to another user!");
        }

        taskRepository.delete(delete);
        return "Task deleted successfully!";
    }

    @Override
    public List<Task> viewAllTask(Long userId) {
        if(taskRepository.findAllByUserId((Long) httpSession.getAttribute("user_id")).size() == 0){
            throw new CustomException("Task List for this user is empty!");
        }
        return taskRepository.findAllByUserId( (Long) httpSession.getAttribute("user_id"));
    }
    @Override
    public List<Task> findTaskById(Long id, Long userId){
        if(taskRepository.findByIdAndUserId(id, (Long) httpSession.getAttribute("user_id")).size() == 0){

            throw new CustomException("Invalid Id or No task with Task ID: " + id);
        }

        return taskRepository.findByIdAndUserId(id, (Long) httpSession.getAttribute("user_id"));
    }

    @Override
    public List<Task> findTaskByStatus(Status status, Long userId){
        if(taskRepository.findAllByStatusAndUserId(status, (Long) httpSession.getAttribute("user_id")).size() == 0){
            throw new CustomException("No available task with status '" + status + "'");
        }
        return taskRepository.findAllByStatusAndUserId(status, (Long) httpSession.getAttribute("user_id"));
    }

}

