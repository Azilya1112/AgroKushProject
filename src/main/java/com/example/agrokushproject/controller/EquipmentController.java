package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.EquipmentDto;
import com.example.agrokushproject.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping("/save")
    public EquipmentDto saveSparePart(@RequestBody EquipmentDto equipmentDto) {
        return equipmentService.saveEquipment(equipmentDto);
    }

    @PutMapping("/update/{id}")
    public EquipmentDto updateSparePart(@RequestBody EquipmentDto equipmentDto){
        return equipmentService.updateEquipment(equipmentDto);
    }

    @GetMapping("/findAll")
    public List<EquipmentDto> findAll() {
        return equipmentService.getAllEquipment();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
    }

}
