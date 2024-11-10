package com.todo.repository;

import com.todo.model.TaskCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskCollectionRepository extends JpaRepository<TaskCollection, Long> {
    Optional<TaskCollection> findByName(String name);
}
