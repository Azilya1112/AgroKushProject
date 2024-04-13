package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.EquipmentStatusDto;

import java.util.List;

public interface EquipmentStatusService {

    EquipmentStatusDto saveEquipmentStatus(EquipmentStatusDto equipmentStatusDto);

    EquipmentStatusDto updateEquipmentStatus(EquipmentStatusDto equipmentStatusDto);

    List<EquipmentStatusDto> findAllEquipmentStatus();

    void deleteEquipmentStatus(Long id);

}
