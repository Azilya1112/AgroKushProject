package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.TaskDto;
import com.example.agrokushproject.dto.TaskStatusDto;
import com.example.agrokushproject.entity.TaskStatus;
import com.example.agrokushproject.mapper.TaskStatusMapper;
import com.example.agrokushproject.repositories.TaskStatusRepository;
import com.example.agrokushproject.service.TaskService;
import com.example.agrokushproject.service.TaskStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskStatusServiceImpl implements TaskStatusService {
    private final TaskStatusRepository taskStatusRepository;

    @Override
    public TaskStatusDto saveTaskStatus(TaskStatusDto taskStatusDto) {
        TaskStatus taskStatus;
        return null;
    }

    @Override
    public TaskStatusDto updateTaskStatus(TaskStatusDto taskStatusDto) {
        return null;
    }

    @Override
    public List<TaskStatusDto> findAllTaskStatus() {
        return null;
    }

    @Override
    public void deleteTaskStatus(Long id) {

    }
}
