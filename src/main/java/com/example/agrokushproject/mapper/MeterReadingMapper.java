package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.MeterReadingDto;
import com.example.agrokushproject.entity.Meter;
import com.example.agrokushproject.entity.MeterReading;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeterReadingMapper {
    MeterReadingMapper INSTANCE = Mappers.getMapper(MeterReadingMapper.class);

    @Mapping(source = "meter.id", target = "meterId")
    MeterReadingDto toDto(MeterReading reading);

    List<MeterReadingDto> toDtoList(List<MeterReading> readings);

    @Mapping(source = "meterId", target = "meter")
    MeterReading toEntity(MeterReadingDto dto);

    default Meter stubMeter(Long id) {
        if (id == null || id <= 0) return null;
        Meter m = new Meter();
        m.setId(id);
        return m;
    }
}
