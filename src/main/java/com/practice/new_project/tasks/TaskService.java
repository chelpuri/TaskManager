package com.practice.new_project.tasks;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private ModelMapper modelMapper;

    public TaskService(TaskRepository taskRepository,ModelMapper modelMapper){
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    public List<TaskEntity> getAllTasks(){
        return taskRepository.findAll();
    }

    public TaskDTO getTaskbyId(Long id){
        var task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException(id));
        return modelMapper.map(task, TaskDTO.class);
    }

    public TaskDTO createTask(TaskDTO task){
        if(task.getDueDate() != null && task.getDueDate().before(new Date())){
            throw new TaskInvalidException("Due date Invalid");
        }
        var taskEntity = modelMapper.map(task, TaskEntity.class);
        var savedTask =  taskRepository.save(taskEntity);
        return modelMapper.map(savedTask, TaskDTO.class);
    }

    public TaskDTO editTaskbyId(long id, TaskDTO task) {
        if(taskRepository.findById(id) == null){
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
        var taskEntity = modelMapper.map(task, TaskEntity.class);
        var savedTask =  taskRepository.save(taskEntity);
        return modelMapper.map(savedTask, TaskDTO.class);
    }


    static class TaskInvalidException extends IllegalArgumentException{
        public TaskInvalidException(String message){
            super(message);
        }
    }

    static class TaskAlreadyException extends IllegalArgumentException{
        public TaskAlreadyException(Long id){
            super("Task with" +id+ " already exists");
        }
    }

    static class TaskNotFoundException extends IllegalArgumentException{
        public TaskNotFoundException(Long id){
            super("Task not Found");
        }
    }

}
