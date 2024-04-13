package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.TaskDto;
import com.example.agrokushproject.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/save")
    public TaskDto saveTask(@RequestBody TaskDto taskDto) {
        return taskService.saveTask(taskDto);
    }

    @PutMapping("/update/{id}")
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        return taskService.updateTask(taskDto);
    }

    @GetMapping("/findAll")
    public List<TaskDto> findAll() {
        return taskService.findAllTask();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


}
