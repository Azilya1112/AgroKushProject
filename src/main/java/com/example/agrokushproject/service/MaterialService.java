package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.MaterialDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MaterialService {
    MaterialDto saveMaterial(MaterialDto materialDto);
    MaterialDto updateMaterial(MaterialDto materialDto);
    MaterialDto getMaterialById(long id);
    Page<MaterialDto> getAllMaterials(String fileName, Pageable pageable);
    void deleteMaterialById(long id);
}
