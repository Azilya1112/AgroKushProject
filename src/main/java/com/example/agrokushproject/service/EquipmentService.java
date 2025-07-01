package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.EquipmentDto;

import java.util.List;

public interface EquipmentService {

    EquipmentDto saveEquipment(EquipmentDto equipmentDto);
    EquipmentDto updateEquipment(EquipmentDto equipmentDto);
    List<EquipmentDto> getAllEquipment();
    void deleteEquipment(Long id);
}
