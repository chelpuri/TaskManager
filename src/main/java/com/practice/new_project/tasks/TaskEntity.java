package com.practice.new_project.tasks;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.practice.new_project.common.BaseEntity;
import com.practice.new_project.notes.NoteEnity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity extends BaseEntity{

    private String name;
    private Date due_date;
    private boolean done;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    List<NoteEnity> notes;
}
