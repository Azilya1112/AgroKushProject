package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.SparePartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SparePartService {
    SparePartDto saveSparePart(SparePartDto sparePartDto);
    SparePartDto updateSparePart(SparePartDto sparePartDto);
    SparePartDto getSparePartById(Long id);
    Page<SparePartDto> getAllSparePart(String name, Pageable pageable);
    void deleteSparePart(Long id);
}
