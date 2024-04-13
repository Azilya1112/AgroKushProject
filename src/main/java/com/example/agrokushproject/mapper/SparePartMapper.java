package com.example.agrokushproject.mapper;


import com.example.agrokushproject.dto.SparePartDto;
import com.example.agrokushproject.entity.SparePart;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SparePartMapper {
    SparePartMapper INSTANCE= Mappers.getMapper(SparePartMapper.class);

    SparePart toEntity(SparePartDto sparePartDto);

    SparePartDto toDto(SparePart sparePart);

    List<SparePartDto> toResponseList(List<SparePart> sparePartList);

    void update(@MappingTarget SparePartDto sparePartDto, SparePart sparePart);


}
