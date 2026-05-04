package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.MeterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MeterService {
    MeterDto saveMeter(MeterDto meterDto);
    MeterDto updateMeter(MeterDto meterDto);
    MeterDto getMeterById(Long id);
    Page<MeterDto> getAllMeters(String name, Long equipmentId, Pageable pageable);
    List<MeterDto> getMetersByEquipmentId(Long equipmentId);
    void deleteMeter(Long id);
}
