package com.practice.new_project.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class taskRepoTests {
    @Autowired private TaskRepository taskRepository;

    @Test
    public void canCreateTask(){
        TaskEntity task=new TaskEntity();
        task.setName("good");
        task.setDue_date(new Date(0));
        taskRepository.save(task);

        assertEquals("good", taskRepository.findAll().get(0).getName());
    }
}
