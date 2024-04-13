package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.TaskDto;
import com.example.agrokushproject.entity.Task;
import com.example.agrokushproject.exceptions.RecordNotFoundException;
import com.example.agrokushproject.mapper.TaskMapper;
import com.example.agrokushproject.repositories.TaskRepository;
import com.example.agrokushproject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    @Override
    public TaskDto saveTask(TaskDto taskDto){

        Task task = TaskMapper.INSTANCE.toEntity(taskDto);
        try {
            Task taskSave = taskRepository.save(task);
            return TaskMapper.INSTANCE.toDto(taskSave);
        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось сохранить задачу в базе!", e);
        }
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        Task task = this.taskRepository.findById(taskDto.getId())
                .orElseThrow(() -> new RuntimeException("Статус задачи не найден"));

        TaskMapper.INSTANCE.update(task, taskDto);

        try{
            Task taskSave=taskRepository.save(task);
            return  TaskMapper.INSTANCE.toDto(taskSave);
        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось обновить задачу в базе!", e);
        }
    }


    @Override
    public List<TaskDto> findAllTask() {
        return TaskMapper.INSTANCE.toResponseList(taskRepository.findAll());
    }

    @Override
    public void deleteTask(Long id){
        Task task = this.taskRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Статус задачи не найден"));

        taskRepository.deleteById(task.getId());
    }


}
