package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.TaskDto;
import com.example.agrokushproject.entity.Equipment;
import com.example.agrokushproject.entity.Location;
import com.example.agrokushproject.entity.Task;
import com.example.agrokushproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "equipments", target = "equipmentIds")
    @Mapping(source = "location.id", target = "locationId")
    TaskDto toDto(Task task);

    List<TaskDto> toDtoList(List<Task> tasks);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "equipmentIds", target = "equipments")
    @Mapping(source = "locationId", target = "location")
    Task toEntity(TaskDto dto);

    List<Task> toEntityList(List<TaskDto> dtos);

    default Set<Long> equipmentsToIds(Set<Equipment> equipments) {
        if (equipments == null) return new HashSet<>();
        return equipments.stream().map(Equipment::getId).collect(Collectors.toSet());
    }

    default Set<Equipment> idsToEquipments(Set<Long> ids) {
        if (ids == null) return new HashSet<>();
        return ids.stream()
                .filter(Objects::nonNull)
                .map(this::createEquipment)
                .collect(Collectors.toSet());
    }

    default User createUser(Long id) {
        if (id == null || id <= 0) return null;
        User u = new User();
        u.setId(id);
        return u;
    }

    default Equipment createEquipment(Long id) {
        if (id == null || id <= 0) return null;
        Equipment e = new Equipment();
        e.setId(id);
        return e;
    }

    default Location createLocation(Long id) {
        if (id == null || id <= 0) return null;
        Location l = new Location();
        l.setId(id);
        return l;
    }
}
