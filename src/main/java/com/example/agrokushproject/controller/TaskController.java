package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.TaskDto;
import com.example.agrokushproject.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<TaskDto> findAll() {
        return taskService.getAllTask();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


}
