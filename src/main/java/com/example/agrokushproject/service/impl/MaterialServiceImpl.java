package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.MaterialDto;
import com.example.agrokushproject.entity.Material;
import com.example.agrokushproject.mapper.MaterialMapper;
import com.example.agrokushproject.repositories.MaterialRepository;
import com.example.agrokushproject.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Override
    @Transactional
    public MaterialDto saveMaterial(MaterialDto materialDto) {
        Material entity = materialMapper.toEntity(materialDto);
        Material saved = materialRepository.save(entity);
        return materialMapper.toDto(saved);
    }

    @Override
    @Transactional
    public MaterialDto updateMaterial(MaterialDto materialDto) {
        Long id = materialDto.getId();
        if (id == null) {
            throw new ResponseStatusException(NOT_FOUND, "Material id must be provided for update");
        }
        Material existing = materialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Material not found with id " + id));

        Material toSave = materialMapper.toEntity(materialDto);
        toSave.setId(existing.getId());

        Material updated = materialRepository.save(toSave);
        return materialMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public MaterialDto getMaterialById(long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Material not found with id " + id));
        return materialMapper.toDto(material);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaterialDto> getAllMaterials() {
        List<Material> list = materialRepository.findAll();
        return materialMapper.toDtoList(list);
    }

    @Override
    @Transactional
    public void deleteMaterialById(long id) {
        if (!materialRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Material not found with id " + id);
        }
        materialRepository.deleteById(id);
    }
}