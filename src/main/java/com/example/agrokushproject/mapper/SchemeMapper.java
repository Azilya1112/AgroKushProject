package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.SchemeDto;
import com.example.agrokushproject.entity.Scheme;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface SchemeMapper {
    SchemeMapper INSTANCE= Mappers.getMapper(SchemeMapper.class);

    Scheme toEntity(SchemeDto schemeDto);

    SchemeDto toDto(Scheme scheme);

    List<SchemeDto> toResponseList(List<Scheme> sparePartList);

    void update(@MappingTarget SchemeDto schemeDto, Scheme scheme);
}
