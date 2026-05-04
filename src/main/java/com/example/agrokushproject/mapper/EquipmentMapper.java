package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.EquipmentDto;
import com.example.agrokushproject.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    EquipmentMapper INSTANCE = Mappers.getMapper(EquipmentMapper.class);

    @Mapping(source = "tasks", target = "taskIds")
    @Mapping(source = "spareParts", target = "sparePartIds")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "techPassport.id", target = "techPassportId")
    @Mapping(source = "defects", target = "defectIds")
    @Mapping(source = "meters", target = "meterIds")
    EquipmentDto toDto(Equipment equipment);

    List<EquipmentDto> toDtoList(List<Equipment> equipments);

    @Mapping(source = "taskIds", target = "tasks")
    @Mapping(source = "sparePartIds", target = "spareParts")
    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "techPassportId", target = "techPassport")
    @Mapping(target = "defects", ignore = true)
    @Mapping(target = "meters", ignore = true)
    Equipment toEntity(EquipmentDto dto);

    List<Equipment> toEntityList(List<EquipmentDto> dtos);

    default Set<Long> tasksToIds(Set<Task> tasks) {
        if (tasks == null) return new HashSet<>();
        return tasks.stream().map(Task::getId).collect(Collectors.toSet());
    }

    default Set<Long> sparePartsToIds(Set<SparePart> spareParts) {
        if (spareParts == null) return new HashSet<>();
        return spareParts.stream().map(SparePart::getId).collect(Collectors.toSet());
    }

    default List<Long> defectsToIds(List<Defect> defects) {
        if (defects == null) return new ArrayList<>();
        return defects.stream().map(Defect::getId).collect(Collectors.toList());
    }

    default List<Long> metersToIds(List<Meter> meters) {
        if (meters == null) return new ArrayList<>();
        return meters.stream().map(Meter::getId).collect(Collectors.toList());
    }

    default Task stubTask(Long id) {
        if (id == null || id <= 0) return null;
        Task t = new Task();
        t.setId(id);
        return t;
    }

    default SparePart stubSparePart(Long id) {
        if (id == null || id <= 0) return null;
        SparePart sp = new SparePart();
        sp.setId(id);
        return sp;
    }

    default Location stubLocation(Long id) {
        if (id == null || id <= 0) return null;
        Location loc = new Location();
        loc.setId(id);
        return loc;
    }

    default Material stubMaterial(Long id) {
        if (id == null || id <= 0) return null;
        Material m = new Material();
        m.setId(id);
        return m;
    }
}
