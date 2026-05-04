package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.TaskDto;
import com.example.agrokushproject.entity.enums.TaskStatus;
import com.example.agrokushproject.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/save")
    public TaskDto saveTask(@Valid @RequestBody TaskDto taskDto) {
        return taskService.saveTask(taskDto);
    }

    @PutMapping("/update/{id}")
    public TaskDto updateTask(@Valid @RequestBody TaskDto taskDto){
        return taskService.updateTask(taskDto);
    }

    @GetMapping("/findAll")
    public Page<TaskDto> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) TaskStatus status,
             @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return taskService.getAllTask(name, status, pageable);
    }

    @GetMapping("/find/{id}")
    public TaskDto findById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


}
