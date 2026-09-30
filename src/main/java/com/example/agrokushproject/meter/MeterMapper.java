package com.example.agrokushproject.meter;

import com.example.agrokushproject.equipment.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeterMapper {
    MeterMapper INSTANCE = Mappers.getMapper(MeterMapper.class);

    @Mapping(source = "equipment.id", target = "equipmentId")
    MeterDto toDto(Meter meter);

    @Mapping(source = "equipmentId", target = "equipment")
    Meter toEntity(MeterDto meterDto);

    List<MeterDto> toDtoList(List<Meter> meters);

    List<Meter> toEntityList(List<MeterDto> dtos);

    default Equipment stubEquipment(Long id) {
        if (id == null || id <= 0) return null;
        Equipment e = new Equipment();
        e.setId(id);
        return e;
    }
}
