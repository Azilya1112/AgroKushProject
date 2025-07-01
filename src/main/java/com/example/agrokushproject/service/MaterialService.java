package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.MaterialDto;

import java.util.List;

public interface MaterialService {

    MaterialDto saveMaterial(MaterialDto materialDto);
    MaterialDto updateMaterial(MaterialDto materialDto);
    MaterialDto getMaterialById(long id);
    List<MaterialDto> getAllMaterials();
    void deleteMaterialById(long id);
}
