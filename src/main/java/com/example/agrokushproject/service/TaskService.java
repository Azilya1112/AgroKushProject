package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.TaskDto;
import com.example.agrokushproject.entity.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    TaskDto saveTask(TaskDto taskDto);
    TaskDto updateTask(TaskDto taskDto);
    TaskDto getTaskById(Long id);
    Page<TaskDto> getAllTask(String name, TaskStatus status, Pageable pageable);
    void deleteTask(Long id);
}
