package com.practice.new_project.notes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.new_project.tasks.TaskEntity;

public interface NoteRepository extends JpaRepository<TaskEntity, Long> {
    
}
