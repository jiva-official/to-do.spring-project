package com.todo.controller;

import com.todo.model.Task;
import com.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    TodoService todoService;

    @GetMapping("/")
    public String hello() {
        return "Hello There from Spring Boot";
    }

    @PostMapping("/addtask")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task taskObj = todoService.createTask(task);
        return new ResponseEntity<>(taskObj, HttpStatus.CREATED);
    }

    @GetMapping("/alltasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> allTasks = todoService.getAllTasks();
        return new ResponseEntity<>(allTasks, HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        Task task = todoService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        Task taskObj = todoService.getTaskById(id);
        taskObj.setTask(task.getTask());
        taskObj.setStatus(task.getStatus());
        taskObj.setDescription(task.getDescription());

        Task updated = todoService.updateTask(taskObj);

        return new ResponseEntity<>(taskObj, HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
        todoService.deleteTask(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
