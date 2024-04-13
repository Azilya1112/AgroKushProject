package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.EquipmentStatusDto;
import com.example.agrokushproject.entity.EquipmentStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EquipmentStatusMapper {
    EquipmentStatusMapper INSTANCE = Mappers.getMapper(EquipmentStatusMapper.class);

    EquipmentStatus toEntity(EquipmentStatusDto equipmentStatusDto);

    EquipmentStatusDto toDto(EquipmentStatus equipmentStatus);

    List<EquipmentStatusDto> toResponseList(List<EquipmentStatus> equipmentStatusList);
    void update(@MappingTarget EquipmentStatus equipmentStatus, EquipmentStatusDto equipmentStatusDto);
}
