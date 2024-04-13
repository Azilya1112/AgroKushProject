package com.example.agrokushproject.service.impl;


import com.example.agrokushproject.dto.EquipmentStatusDto;
import com.example.agrokushproject.entity.EquipmentStatus;
import com.example.agrokushproject.exceptions.RecordNotFoundException;
import com.example.agrokushproject.mapper.EquipmentStatusMapper;
import com.example.agrokushproject.repositories.EquipmentStatusRepository;
import com.example.agrokushproject.service.EquipmentStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentStatusServiceImpl implements EquipmentStatusService {
    private final EquipmentStatusRepository equipmentStatusRepository;

    @Override
    public EquipmentStatusDto saveEquipmentStatus(EquipmentStatusDto equipmentStatusDto){

        EquipmentStatus equipmentStatus = EquipmentStatusMapper.INSTANCE.toEntity(equipmentStatusDto);
        try {
            EquipmentStatus equipmentStatusSave = equipmentStatusRepository.save(equipmentStatus);
            return EquipmentStatusMapper.INSTANCE.toDto(equipmentStatusSave);
        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось сохранить в базе!", e);
        }

    }

    @Override
    public EquipmentStatusDto updateEquipmentStatus(EquipmentStatusDto equipmentStatusDto) {
        EquipmentStatus equipmentStatus = this.equipmentStatusRepository.findById(equipmentStatusDto.getId())
                .orElseThrow(() -> new RuntimeException("Статус оборудования не найден"));

        EquipmentStatusMapper.INSTANCE.update(equipmentStatus, equipmentStatusDto);

        try{
            EquipmentStatus equipmentStatusSave=equipmentStatusRepository.save(equipmentStatus);
            return  EquipmentStatusMapper.INSTANCE.toDto(equipmentStatusSave);
        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось обновить в базе!", e);
        }
    }
    @Override
    public List<EquipmentStatusDto> findAllEquipmentStatus() {
        return EquipmentStatusMapper.INSTANCE.toResponseList(equipmentStatusRepository.findAll());
    }

    @Override
    public void deleteEquipmentStatus(Long id) {
        EquipmentStatus equipmentStatus = this.equipmentStatusRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Статус оборудования не найден"));
        equipmentStatusRepository.deleteById(id);

    }

}
