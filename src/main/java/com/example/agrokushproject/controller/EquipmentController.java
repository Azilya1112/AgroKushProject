package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.EquipmentDto;
import com.example.agrokushproject.entity.enums.EquipmentStatus;
import com.example.agrokushproject.service.EquipmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping("/save")
    public EquipmentDto saveSparePart(@Valid @RequestBody EquipmentDto equipmentDto) {
        return equipmentService.saveEquipment(equipmentDto);
    }

    @PutMapping("/update/{id}")
    public EquipmentDto updateSparePart(@Valid @RequestBody EquipmentDto equipmentDto){
        return equipmentService.updateEquipment(equipmentDto);
    }

    @GetMapping("/findAll")
    public Page<EquipmentDto> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) EquipmentStatus status,
             @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return equipmentService.getAllEquipment(name, status, pageable);
    }

    @GetMapping("/find/{id}")
    public EquipmentDto findById(@PathVariable Long id) {
        return equipmentService.getEquipmentById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
    }

}
