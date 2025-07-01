package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.SparePartDto;
import com.example.agrokushproject.entity.SparePart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SparePartMapper {
    SparePartMapper INSTANCE = Mappers.getMapper(SparePartMapper.class);

    SparePartDto toDto(SparePart sparePart);

    SparePart toEntity(SparePartDto dto);

    List<SparePartDto> toDtoList(List<SparePart> spareParts);
}