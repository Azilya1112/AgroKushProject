package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.EquipmentStatusDto;
import com.example.agrokushproject.service.EquipmentStatusService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipmentStatus")
public class EquipmentStatusController {

    private final EquipmentStatusService equipmentStatusService;

    @PostMapping("/save")
    public EquipmentStatusDto saveEquipmentStatus(@RequestBody EquipmentStatusDto equipmentStatusDto) {
        return equipmentStatusService.saveEquipmentStatus(equipmentStatusDto);
    }

    @PutMapping("/update/{id}")
    public EquipmentStatusDto updateEquipmentStatus(@RequestBody EquipmentStatusDto equipmentStatusDto){
        return equipmentStatusService.updateEquipmentStatus(equipmentStatusDto);
    }

    @GetMapping("/findAll")
    public List<EquipmentStatusDto> findAll() {
        return equipmentStatusService.findAllEquipmentStatus();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEquipmentStatus(@PathVariable Long id) {
        equipmentStatusService.deleteEquipmentStatus(id);
    }
}
