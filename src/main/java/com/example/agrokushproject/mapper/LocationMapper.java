package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.LocationDto;
import com.example.agrokushproject.entity.Equipment;
import com.example.agrokushproject.entity.Location;
import com.example.agrokushproject.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    LocationDto toDto(Location location);

    List<LocationDto> toDtoList(List<Location> locations);

    Location toEntity(LocationDto dto);

    List<Location> toEntityList(List<LocationDto> dtos);


    default Equipment stubEquipment(Long id) {
        Equipment e = new Equipment();
        e.setId(id);
        return e;
    }

    default Task stubTask(Long id) {
        Task t = new Task();
        t.setId(id);
        return t;
    }
}
