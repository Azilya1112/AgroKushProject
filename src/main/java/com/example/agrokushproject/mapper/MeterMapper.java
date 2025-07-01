package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.MeterDto;
import com.example.agrokushproject.entity.Meter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeterMapper {
    MeterMapper INSTANCE = Mappers.getMapper(MeterMapper.class);

    MeterDto toDto(Meter meter);

    Meter toEntity(MeterDto MeterDto);

    List<MeterDto> toDtoList(List<Meter> meters);

    List<Meter> toEntityList(List<MeterDto> dtos);
}
