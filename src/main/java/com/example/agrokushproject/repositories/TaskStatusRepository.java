package com.example.agrokushproject.repositories;

import com.example.agrokushproject.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
}
