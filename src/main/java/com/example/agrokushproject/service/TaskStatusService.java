package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.TaskStatusDto;

import java.util.List;

public interface TaskStatusService {
    TaskStatusDto saveTaskStatus(TaskStatusDto taskStatusDto);
    TaskStatusDto updateTaskStatus(TaskStatusDto taskStatusDto);
    List<TaskStatusDto> findAllTaskStatus();
    void deleteTaskStatus(Long id);
}
