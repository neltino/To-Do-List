package com.example.todoapp.controller;

import com.example.todoapp.CustomException.CustomException;
import com.example.todoapp.dto.TaskDto;
import com.example.todoapp.enums.Status;
import com.example.todoapp.model.Task;
import com.example.todoapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final HttpSession httpSession;

    @PostMapping("/task/add")
    public String addTask(@Valid @RequestBody TaskDto taskDto) {
       if(httpSession.getAttribute("user_id") == null){
           throw new CustomException("Please, login first");
       }
        return taskService.createTask(taskDto);
    }
    @PatchMapping("/task/updatetitle")
    public String updateTaskTitle(@RequestParam Long id, @RequestParam String title){

        if(httpSession.getAttribute("user_id") == null){
            throw new CustomException("Please, login first");
        }
        return taskService.updateTaskTitle(id, title);
    }


    @PatchMapping("/task/updatedesc")
    public String updateTaskDesc(@RequestParam Long id, @RequestParam String desc){

        if(httpSession.getAttribute("user_id") == null){
            throw new CustomException("Please, login first");
        }
        return taskService.updateTaskDesc(id, desc);
    }

    @PatchMapping("/task/update")
    public String updateTask(@RequestParam Long id, @RequestParam String title, @RequestParam String desc) {

        if(httpSession.getAttribute("user_id") == null){
            throw new CustomException("Please, login first");
        }
        return taskService.updateTask(id, title, desc);
    }

    @PatchMapping("task/status/toProgress")
    public String ToProgress(@RequestParam Long id){
        if(httpSession.getAttribute("user_id") == null){
            throw new CustomException("Please, login first");
        }
        return taskService.ToInProgress(id);
    }

    @PatchMapping("task/status/toCompleted")
    public String ToCompleted(@RequestParam Long id){
        if(httpSession.getAttribute("user_id") == null){
            throw new CustomException("Please, login first");
        }
        return taskService.ToCompleted(id);
    }

    @PatchMapping("task/status/toPending")
    public String ToPending(@RequestParam Long id){
        if(httpSession.getAttribute("user_id") == null){
            throw new CustomException("Please, login first");
        }
        return taskService.ToPending(id);
    }

    @DeleteMapping("task/delete")
    public String deleteTask(@RequestParam Long id){
        if(httpSession.getAttribute("user_id") == null){
            throw new CustomException("Please, login first");
        }
        return taskService.deleteTask(id);
    }

    @GetMapping("/view")
    public List<Task> viewAllTask(Long userId){
        if(httpSession.getAttribute("user_id") == null){
            throw new CustomException("Please, login first");
        }

        return taskService.viewAllTask((Long) httpSession.getAttribute("user_id"));
    }
    @GetMapping("/view/byid")
    public List<Task> viewTaskById(@RequestParam Long id, Long userId){
        if(httpSession.getAttribute("user_id") == null){
            throw new CustomException("Please, login first");
        }

        return taskService.findTaskById(id, (Long) httpSession.getAttribute("user_id"));
    }

    @GetMapping("view/pending")
    public List<Task> viewPending(Status status, Long userId){
        if(httpSession.getAttribute("user_id") == null){
            throw new CustomException("Please, login first");
        }
        return taskService.findTaskByStatus(Status.PENDING, (Long) httpSession.getAttribute("user_id"));
    }

    @GetMapping("view/progress")
    public List<Task> viewInProgress(Status status, Long userId){
        if(httpSession.getAttribute("user_id") == null){
            throw new CustomException("Please, login first");
        }
        return taskService.findTaskByStatus(Status.IN_PROGRESS, (Long) httpSession.getAttribute("user_id"));
    }


    @GetMapping("view/completed")
    public List<Task> viewCompleted(Status status, Long userId){
        if(httpSession.getAttribute("user_id") == null){
            throw new CustomException("Please, login first");
        }
        return taskService.findTaskByStatus(Status.DONE, (Long) httpSession.getAttribute("user_id"));
    }


}
