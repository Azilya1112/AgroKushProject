package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.TaskDto;
import com.example.agrokushproject.entity.Task;
import com.example.agrokushproject.mapper.TaskMapper;
import com.example.agrokushproject.repositories.TaskRepository;
import com.example.agrokushproject.entity.enums.TaskStatus;
import com.example.agrokushproject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    public Page<TaskDto> getAllTask(String name, TaskStatus status, Pageable pageable) {
        Specification<Task> spec = (root, q, cb) -> cb.conjunction();
        if (name != null && !name.isBlank()) {
            spec = spec.and((root, q, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }
        if (status != null) {
            spec = spec.and((root, q, cb) -> cb.equal(root.get("taskStatus"), status));
        }
        return taskRepository.findAll(spec, pageable).map(taskMapper::toDto);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Task not found with id " + id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Task not found with id " + id));
        return taskMapper.toDto(task);
    
    }
}