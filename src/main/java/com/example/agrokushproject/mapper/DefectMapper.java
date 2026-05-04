package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.DefectDto;
import com.example.agrokushproject.entity.Defect;
import com.example.agrokushproject.entity.Equipment;
import com.example.agrokushproject.entity.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DefectMapper {
    DefectMapper INSTANCE = Mappers.getMapper(DefectMapper.class);
    
    @Mapping(source = "equipment.id", target = "equipmentId")
    @Mapping(source = "images", target = "imageIds")
    DefectDto toDto(Defect defect);

    List<DefectDto> toDtoList(List<Defect> defects);

    @Mapping(source = "equipmentId", target = "equipment")
    @Mapping(source = "imageIds", target = "images")
    Defect toEntity(DefectDto dto);

    List<Defect> toEntityList(List<DefectDto> dtos);

    default Equipment stubEquipment(Long id) {
        if (id == null || id <= 0) return null;
        Equipment e = new Equipment();
        e.setId(id);
        return e;
    }

    default Long materialToId(Material m) {
        return m == null ? null : m.getId();
    }

    default Material stubMaterial(Long id) {
        if (id == null || id <= 0) return null;
        Material m = new Material();
        m.setId(id);
        return m;
    }
}
