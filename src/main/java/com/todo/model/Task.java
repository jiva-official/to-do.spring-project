package com.todo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Task Title cannot be empty")
    private String task;
    private String description;
    private Status status = Status.TODO;

    @ManyToOne
    @JoinColumn(name = "task_collection_id")
    @JsonBackReference
    private TaskCollection taskCollection;
}
