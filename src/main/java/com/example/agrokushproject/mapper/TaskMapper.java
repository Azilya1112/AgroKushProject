package com.example.agrokushproject.mapper;


import com.example.agrokushproject.dto.TaskDto;
import com.example.agrokushproject.entity.Task;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "taskStatusId", target = "taskStatus.id")
    @Mapping(source= "userId", target = "user.id")
    Task toEntity(TaskDto taskDto);

    TaskDto toDto(Task task);

    List<TaskDto> toResponseList(List<Task> taskList);
    void update(@MappingTarget Task task, TaskDto taskDto);

}
