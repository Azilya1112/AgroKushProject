package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.TaskDto;
import com.example.agrokushproject.entity.Task;

import java.util.List;

public interface TaskService {
    TaskDto saveTask(TaskDto taskDto);
    TaskDto updateTask(TaskDto taskDto);
    List<TaskDto> findAllTask();
    void deleteTask(Long id);

}
