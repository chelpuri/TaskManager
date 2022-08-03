package com.taskmanager.tskmanager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.tskmanager.pojos.Task;

@RestController("/tasks")
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();

    @PostMapping("/")
    public Task createTask(@RequestBody Task task) {
        tasks.add(task);
        return task;
    }


    @GetMapping("/{task_id}")
    public Task getTask(@PathVariable("task_id") int id) {
        if(id >= tasks.size()) {
            throw new RuntimeException("Invalid Id");
        }

        return tasks.get(id);
    }


    @PatchMapping(path = "/{task_id}")
    public String editTask(@PathVariable("task_id") int id,@RequestBody Task task){
        if(id >= tasks.size()) {
            return "Invalid Entry";
        }

        
        Task t=tasks.get(id);
        t.setCompleted(task.getCompleted());
        t.setNotes(task.getNotes());
        return "Task Edited";
    }


    @DeleteMapping(path ="/{task_id}")
    public String removeTask(@PathVariable("task_id") int id){
        if(tasks.isEmpty() || id >= tasks.size()){
            return "Invaild Entry/Tasks list is Empty";
        }


        tasks.remove(id);
        return "Task Deleted";
    }

    @GetMapping("/completed/{iscomp}")
    public List<Task> getCompletedTask(@PathVariable("iscomp") String isCompleted)
    {
        List<Task> ans = new ArrayList<>();
        for(Task t:tasks)
        {
            if(t.getCompleted().equals(isCompleted))
            {
                ans.add(t);
            }
        }
        if(ans.isEmpty()){
            return null;
        }

        return ans;
    }
}
