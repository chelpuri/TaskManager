package com.taskmanager.tskmanager.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {
    private int id;
    private String title;
    private String description;
    private String deadline;
    private List<Note> notes=new ArrayList<>();
    private String completed;
}
