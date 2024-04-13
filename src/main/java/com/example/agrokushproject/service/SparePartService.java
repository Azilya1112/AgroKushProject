package com.example.agrokushproject.service;


import com.example.agrokushproject.dto.SparePartDto;

import java.util.List;

public interface SparePartService {
    SparePartDto saveSparePart(SparePartDto sparePartDto);
    SparePartDto updateSparePart(SparePartDto sparePartDto);
    List<SparePartDto> findAllSparePart();
    void deleteSparePart(Long id);

}
