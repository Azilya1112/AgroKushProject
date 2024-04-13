package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.SparePartDto;
import com.example.agrokushproject.dto.TaskStatusDto;
import com.example.agrokushproject.entity.SparePart;
import com.example.agrokushproject.entity.TaskStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskStatusMapper {

    TaskStatusMapper INSTANCE= Mappers.getMapper(TaskStatusMapper.class);

    SparePartDto toEntity(SparePart sparePart);

    SparePart toDto(SparePartDto sparePartDto);
    List<SparePartDto> toResponceList(List<SparePart> sparePartList);

    void update(@MappingTarget TaskStatus taskStatus, TaskStatusDto taskStatusDto);

}
