package com.todo.service;

import com.todo.exception.DuplicateResourceException;
import com.todo.exception.ResourceNotFoundException;
import com.todo.model.Task;
import com.todo.model.TaskCollection;
import com.todo.model.TaskCollectionDTO;
import com.todo.model.TaskDTO;
import com.todo.repository.TaskCollectionRepository;
import com.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionService {

    @Autowired
    TaskCollectionRepository taskCollectionRepository;

    @Autowired
    TodoRepository todoRepository;

    public TaskCollectionDTO createCollection(String name) {
        if (taskCollectionRepository.findByName(name).isPresent()) {
            throw new DuplicateResourceException("Collection with name '" + name + "' already exists.");
        }
        TaskCollection created = new TaskCollection();
        created.setName(name);
        TaskCollection savedCollection = taskCollectionRepository.save(created);
        return new TaskCollectionDTO(savedCollection);
    }

    public TaskDTO addTaskToCollection(Long id, Task task) {
        TaskCollection collection = taskCollectionRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Collection Not Found"));
        task.setTaskCollection(collection);

        Task savedTask = todoRepository.save(task);
        return new TaskDTO(savedTask);
    }

    public List<TaskDTO> getAllTasksInCollection(Long collectionId) {
        List<Task> tasks = todoRepository.findByTaskCollectionId(collectionId);
        return tasks.stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    public void deleteCollection(Long id) {
        taskCollectionRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Collection Not Found"));
        taskCollectionRepository.deleteById(id);
    }

    public List<TaskCollectionDTO> getAllCollections() {
        List<TaskCollection> collections = taskCollectionRepository.findAll();
        return collections.stream().map(TaskCollectionDTO::new).collect(Collectors.toList());
    }
}
