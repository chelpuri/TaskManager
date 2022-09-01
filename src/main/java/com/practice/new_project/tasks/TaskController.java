package com.practice.new_project.tasks;

import java.lang.Thread.State;
import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.new_project.common.ErrorResponseDTO;

import lombok.var;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("")
    ResponseEntity<java.util.List<TaskEntity>> getAllTasks(){
        var task  = taskService.getAllTasks();
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO task){
        var savedtask = taskService.createTask(task);
        return ResponseEntity.created(URI.create("/tasks/"+ savedtask.getId())).body(savedtask);
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskDTO> getTaskbyID(@PathVariable long id){
        var task = taskService.getTaskbyId(id);
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    BodyBuilder editTaskbyID(@PathVariable long id, @RequestBody TaskDTO task){
        var status= taskService.editTaskbyId(id, task);
        return ResponseEntity.accepted();
    }

    @ExceptionHandler({
        TaskService.TaskAlreadyException.class,
        TaskService.TaskInvalidException.class,
        TaskService.TaskNotFoundException.class

    })

    ResponseEntity<ErrorResponseDTO> handleError(Exception e){
        HttpStatus errorStatus;
        if(e instanceof TaskService.TaskNotFoundException){
            errorStatus = HttpStatus.NOT_FOUND;
        }
        else if(e instanceof TaskService.TaskAlreadyException){
            errorStatus = HttpStatus.CONFLICT;
        }
        else if(e instanceof TaskService.TaskInvalidException){
            errorStatus = HttpStatus.BAD_REQUEST;
        }
        else{
            errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(errorStatus).body(new ErrorResponseDTO(e.getMessage()));
    }
}
