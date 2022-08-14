package com.practice.new_project.notes;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.practice.new_project.common.BaseEntity;
import com.practice.new_project.tasks.TaskEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "notes")
public class NoteEnity extends BaseEntity{

    private String title;
    private String body;   

    @ManyToOne
    @JoinColumn(name = "task_id")
    TaskEntity task;
}
