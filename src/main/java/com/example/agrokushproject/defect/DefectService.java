package com.example.agrokushproject.defect;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DefectService {
    DefectDto saveDefect(DefectDto defectDto);
    DefectDto updateDefect(DefectDto defectDto);
    DefectDto getDefectById(Long id);
    Page<DefectDto> getAllDefects(String name, DefectStatus defectStatus, Long equipmentId, Pageable pageable);
    List<DefectDto> getDefectsByDefectId(Long id);
    List<DefectDto> getDefectsByEquipmentId(Long equipmentId);
    DefectDto updateDefectStatus(Long id, DefectStatus status);
    void deleteDefect(Long id);
}
