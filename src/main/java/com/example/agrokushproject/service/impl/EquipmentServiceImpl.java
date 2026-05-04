package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.EquipmentDto;
import com.example.agrokushproject.entity.Equipment;
import com.example.agrokushproject.entity.enums.EquipmentStatus;
import com.example.agrokushproject.mapper.EquipmentMapper;
import com.example.agrokushproject.repositories.EquipmentRepository;
import com.example.agrokushproject.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    @Override
    @Transactional
    public EquipmentDto saveEquipment(EquipmentDto equipmentDto) {
        log.info("Saving equipment: {}", equipmentDto.getEquipmentName());
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
        log.info("Updating equipment with id: {}", id);
        Equipment existing = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Equipment not found with id " + id));

        Equipment toSave = equipmentMapper.toEntity(equipmentDto);
        toSave.setId(existing.getId());

        Equipment updated = equipmentRepository.save(toSave);
        return equipmentMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EquipmentDto> getAllEquipment(String name, EquipmentStatus status, Pageable pageable) {
        log.debug("Fetching equipment, name={}, status={}", name, status);
        Specification<Equipment> spec = (root, q, cb) -> cb.conjunction();
        if (name != null && !name.isBlank()) {
            spec = spec.and((root, q, cb) ->
                cb.like(cb.lower(root.get("equipmentName")), "%" + name.toLowerCase() + "%"));
        }
        if (status != null) {
            spec = spec.and((root, q, cb) -> cb.equal(root.get("equipmentStatus"), status));
        }
        return equipmentRepository.findAll(spec, pageable).map(equipmentMapper::toDto);
    }

    @Override
    @Transactional
    public void deleteEquipment(Long id) {
        if (!equipmentRepository.existsById(id)) {
            log.warn("Equipment not found with id: {}", id);
            throw new ResponseStatusException(NOT_FOUND, "Equipment not found with id " + id);
        }
            log.info("Deleting equipment with id: {}", id);
            equipmentRepository.deleteById(id);
        }

    @Override
    public EquipmentDto getEquipmentById(Long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Equipment not found with id " + id));
        return equipmentMapper.toDto(equipment);}
    }