package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.DefectDto;
import com.example.agrokushproject.entity.Defect;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


import java.util.List;

@Mapper(componentModel = "spring")
public interface DefectMapper {
    DefectMapper INSTANCE = Mappers.getMapper(DefectMapper.class);

    DefectDto toDto(Defect defect);

    List<DefectDto> toDtoList(List<Defect> defects);

    Defect toEntity(DefectDto dto);

    List<Defect> toEntityList(List<DefectDto> dtos);
}