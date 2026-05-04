package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.DefectDto;
import com.example.agrokushproject.entity.enums.DefectStatus;
import com.example.agrokushproject.service.DefectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/defect")
public class DefectController {

    private final DefectService defectService;

    @PostMapping("/save")
    public DefectDto saveDefect(@Valid @RequestBody DefectDto defectDto) {
        return defectService.saveDefect(defectDto);
    }

    @PutMapping("/update/{id}")
    public DefectDto updateDefect(@Valid @RequestBody DefectDto defectDto){
        return defectService.updateDefect(defectDto);
    }

    @GetMapping("/findAll")
    public Page<DefectDto> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long equipmentId,
             @RequestParam(required = false) DefectStatus defectStatus,
            @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return defectService.getAllDefects(name, defectStatus, equipmentId, pageable);
    }

    @GetMapping("/findByEquipment/{equipmentId}")
    public List<DefectDto> findByEquipment(@PathVariable Long equipmentId) {
        return defectService.getDefectsByEquipmentId(equipmentId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDefect(@PathVariable Long id) {
        defectService.deleteDefect(id);
    }

}
