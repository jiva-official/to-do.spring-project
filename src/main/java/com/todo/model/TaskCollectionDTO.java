package com.todo.model;

import lombok.Data;

@Data
public class TaskCollectionDTO {
    private Long id;
    private String name;

    // Constructor to convert from TaskCollection entity
    public TaskCollectionDTO(TaskCollection taskCollection) {
        this.id = taskCollection.getId();
        this.name = taskCollection.getName();
    }
}
