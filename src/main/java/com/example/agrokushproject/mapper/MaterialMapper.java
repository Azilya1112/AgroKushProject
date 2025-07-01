package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.MaterialDto;
import com.example.agrokushproject.entity.Defect;
import com.example.agrokushproject.entity.Equipment;
import com.example.agrokushproject.entity.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

    MaterialDto toDto(Material material);

    List<MaterialDto> toDtoList(List<Material> materials);

    Material toEntity(MaterialDto dto);


    default Defect stubDefect(Long id) {
        Defect d = new Defect();
        d.setId(id);
        return d;
    }

    default Equipment stubEquipment(Long id) {
        Equipment e = new Equipment();
        e.setId(id);
        return e;
    }
}
