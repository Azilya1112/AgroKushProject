package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.CounterDto;
import com.example.agrokushproject.dto.EquipmentStatusDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CounterService {
    CounterDto saveCounter(CounterDto counterDto);
    CounterDto updateCounter(CounterDto counterDto);
    List<CounterDto> findAllCounter();
    void deleteCounter(Long id);
}
