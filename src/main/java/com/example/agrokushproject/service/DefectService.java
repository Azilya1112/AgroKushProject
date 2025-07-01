package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.DefectDto;

import java.util.List;

public interface DefectService {

    DefectDto saveDefect(DefectDto defectDto);
    DefectDto updateDefect(DefectDto defectDto);
    List<DefectDto> getAllDefects();
    List<DefectDto> getDefectsByDefectId(Long id);
    void deleteDefect(Long id);
}
