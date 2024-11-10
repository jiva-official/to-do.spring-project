package com.todo.repository;

import com.todo.model.Task;
import com.todo.model.TaskCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskCollectionId(Long collectionId);
}

