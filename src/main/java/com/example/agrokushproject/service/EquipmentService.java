package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.EquipmentDto;
import com.example.agrokushproject.entity.enums.EquipmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EquipmentService {
    EquipmentDto saveEquipment(EquipmentDto equipmentDto);
    EquipmentDto updateEquipment(EquipmentDto equipmentDto);
    EquipmentDto getEquipmentById(Long id);
    Page<EquipmentDto> getAllEquipment(String name, EquipmentStatus status, Pageable pageable);
    void deleteEquipment(Long id);
}
