package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.CounterDto;
import com.example.agrokushproject.entity.Counter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CounterMapper {
    CounterMapper INSTANCE = Mappers.getMapper(CounterMapper.class);
    Counter toEntity(CounterDto counterDto);

    CounterDto toDto(Counter counter);

    List<CounterDto> toResponseList(List<Counter> taskList);

    void update(@MappingTarget Counter counter, CounterDto counterDto);

}
