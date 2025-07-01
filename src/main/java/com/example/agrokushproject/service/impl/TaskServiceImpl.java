package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.TaskDto;
import com.example.agrokushproject.entity.Task;
import com.example.agrokushproject.exceptions.RecordNotFoundException;
import com.example.agrokushproject.mapper.TaskMapper;
import com.example.agrokushproject.repositories.TaskRepository;
import com.example.agrokushproject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskDto saveTask(TaskDto taskDto) {
        Task toSave = taskMapper.toEntity(taskDto);
        Task saved = taskRepository.save(toSave);
        return taskMapper.toDto(saved);
    }

    @Override
    @Transactional
    public TaskDto updateTask(TaskDto taskDto) {
        Long id = taskDto.getId();
        if (id == null) {
            throw new ResponseStatusException(NOT_FOUND, "Task id must be provided for update");
        }
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Task not found with id " + id));
        Task toSave = taskMapper.toEntity(taskDto);
        toSave.setId(existing.getId());

        Task updated = taskRepository.save(toSave);
        return taskMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getAllTask() {
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.toDtoList(tasks);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Task not found with id " + id);
        }
        taskRepository.deleteById(id);
    }
}