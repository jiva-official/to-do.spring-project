package com.todo.model;

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
}
