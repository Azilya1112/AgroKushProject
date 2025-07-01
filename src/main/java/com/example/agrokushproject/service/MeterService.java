package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.MeterDto;

import java.util.List;


public interface MeterService {
    MeterDto saveMeter(MeterDto meterDto);
    MeterDto updateMeter(MeterDto meterDto);
    List<MeterDto> getAllMeters();
    void deleteMeter(Long id);
}
