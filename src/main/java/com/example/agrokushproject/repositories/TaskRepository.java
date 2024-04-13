package com.example.agrokushproject.repositories;

import com.example.agrokushproject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>{
}
