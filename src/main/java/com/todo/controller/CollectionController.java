package com.todo.controller;

import com.todo.model.Task;
import com.todo.model.TaskCollection;
import com.todo.model.TaskCollectionDTO;
import com.todo.model.TaskDTO;
import com.todo.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @PostMapping("/addcollection")
    public ResponseEntity<TaskCollectionDTO> createCollection(@RequestParam String name) {
        TaskCollectionDTO taskCollection = collectionService.createCollection(name);
        return new ResponseEntity<>(taskCollection, HttpStatus.CREATED);
    }

    @PostMapping("/collections/{id}/tasks")
    public ResponseEntity<TaskDTO> addTaskToCollection(@PathVariable Long id, @RequestBody Task task) {
        TaskDTO createdTask = collectionService.addTaskToCollection(id, task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/collections/{id}/tasks")
    public ResponseEntity<List<TaskDTO>> getTaskFromCollection(@PathVariable Long id) {
        List<TaskDTO> tasks = collectionService.getAllTasksInCollection(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("/collections/{id}")
    public ResponseEntity<String> deleteCollection(@PathVariable Long id) {
        collectionService.deleteCollection(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    @GetMapping("/allCollections")
    public ResponseEntity<List<TaskCollectionDTO>> getAllCollections() {
        List<TaskCollectionDTO> allCollections = collectionService.getAllCollections();
        return new ResponseEntity<>(allCollections, HttpStatus.OK);
    }

}
