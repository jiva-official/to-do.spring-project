package com.todo.model;

import lombok.Data;

@Data
public class TaskDTO {
    private Long id;
    private String task;
    private String description;
    private Status status;
    private TaskCollectionDTO taskCollection;

    // Constructor to convert from Task entity
    public TaskDTO(Task task) {
        this.id = task.getId();
        this.task = task.getTask();
        this.description = task.getDescription();
        this.status = task.getStatus();
        if (task.getTaskCollection() != null) {
            this.taskCollection = new TaskCollectionDTO(task.getTaskCollection());
        }
    }

    // Getters and setters
}
