package com.example.agrokushproject.material;

import com.example.agrokushproject.defect.Defect;
import com.example.agrokushproject.equipment.Equipment;
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
