package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.SparePartDto;
import com.example.agrokushproject.entity.Equipment;
import com.example.agrokushproject.entity.SparePart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SparePartMapper {
    SparePartMapper INSTANCE = Mappers.getMapper(SparePartMapper.class);

    @Mapping(source = "equipments", target = "equipmentIds")
    @Mapping(source = "equipments", target = "equipmentNames")
    SparePartDto toDto(SparePart sparePart);

    @Mapping(target = "equipments", ignore = true)
    SparePart toEntity(SparePartDto dto);

    List<SparePartDto> toDtoList(List<SparePart> spareParts);

    default Set<Long> equipmentsToIds(Set<Equipment> equipments) {
        if (equipments == null) return new HashSet<>();
        return equipments.stream().map(Equipment::getId).collect(Collectors.toSet());
    }

    default List<String> equipmentsToNames(Set<Equipment> equipments) {
        if (equipments == null) return new ArrayList<>();
        return equipments.stream().map(Equipment::getEquipmentName).collect(Collectors.toList());
    }
}
