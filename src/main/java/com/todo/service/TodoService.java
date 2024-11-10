package com.todo.service;

import com.todo.model.Task;
import com.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public Task createTask(@RequestBody Task task) {
        return todoRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return todoRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return todoRepository.findById(id).orElseThrow(()->new RuntimeException("task not found with id: "+id));
    }

    public Task updateTask(Task taskObj) {
        return todoRepository.save(taskObj);
    }

    public void deleteTask(Long id) {
        this.getTaskById(id);
        todoRepository.deleteById(id);
    }
}
