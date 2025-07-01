package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.EquipmentDto;
import com.example.agrokushproject.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    EquipmentMapper INSTANCE = Mappers.getMapper(EquipmentMapper.class);

    EquipmentDto toDto(Equipment equipment);

    List<EquipmentDto> toDtoList(List<Equipment> equipments);

    Equipment toEntity(EquipmentDto dto);

    List<Equipment> toEntityList(List<EquipmentDto> dtos);

    default Task stubTask(Long id) {
        Task t = new Task();
        t.setId(id);
        return t;
    }

    default SparePart stubSparePart(Long id) {
        SparePart sp = new SparePart();
        sp.setId(id);
        return sp;
    }

    default Location stubLocation(Long id) {
        Location loc = new Location();
        loc.setId(id);
        return loc;
    }

    default Material stubMaterial(Long id) {
        Material m = new Material();
        m.setId(id);
        return m;
    }
}