package com.example.agrokushproject.meterreading;

import com.example.agrokushproject.meter.Meter;
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
