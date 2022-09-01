package com.practice.new_project.tasks;

import java.sql.Date;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class TaskDTO {
    @Nullable
    Long id;
    @Nullable
    String name;
    @Nullable
    Date dueDate;

    @Nullable
    boolean done;
}
