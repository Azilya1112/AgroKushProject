package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.EquipmentDto;
import com.example.agrokushproject.entity.Equipment;
import com.example.agrokushproject.mapper.EquipmentMapper;
import com.example.agrokushproject.repositories.EquipmentRepository;
import com.example.agrokushproject.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    @Override
    @Transactional
    public EquipmentDto saveEquipment(EquipmentDto equipmentDto) {
        Equipment entity = equipmentMapper.toEntity(equipmentDto);
        Equipment saved = equipmentRepository.save(entity);
        return equipmentMapper.toDto(saved);
    }

    @Override
    @Transactional
    public EquipmentDto updateEquipment(EquipmentDto equipmentDto) {
        Long id = equipmentDto.getId();
        if (id == null) {
            throw new ResponseStatusException(NOT_FOUND, "Equipment id must be provided for update");
        }
        Equipment existing = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Equipment not found with id " + id));

        Equipment toSave = equipmentMapper.toEntity(equipmentDto);
        toSave.setId(existing.getId());

        Equipment updated = equipmentRepository.save(toSave);
        return equipmentMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EquipmentDto> getAllEquipment() {
        List<Equipment> all = equipmentRepository.findAll();
        return equipmentMapper.toDtoList(all);
    }

    @Override
    @Transactional
    public void deleteEquipment(Long id) {
        if (!equipmentRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Equipment not found with id " + id);
        }
        equipmentRepository.deleteById(id);
    }
}
