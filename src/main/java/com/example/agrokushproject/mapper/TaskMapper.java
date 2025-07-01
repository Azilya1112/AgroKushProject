package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.TaskDto;
import com.example.agrokushproject.entity.Equipment;
import com.example.agrokushproject.entity.Location;
import com.example.agrokushproject.entity.Task;
import com.example.agrokushproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto toDto(Task task);

    List<TaskDto> toDtoList(List<Task> tasks);
    Task toEntity(TaskDto dto);

    List<Task> toEntityList(List<TaskDto> dtos);


    default User createUser(Long id) {
        User u = new User();
        u.setId(id);
        return u;
    }

    default Equipment createEquipment(Long id) {
        Equipment e = new Equipment();
        e.setId(id);
        return e;
    }

    default Location createLocation(Long id) {
        Location l = new Location();
        l.setId(id);
        return l;
    }
}

