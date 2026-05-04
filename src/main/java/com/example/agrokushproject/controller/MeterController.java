package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.MeterDto;
import com.example.agrokushproject.service.MeterService;
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
@RequestMapping("/meter")
public class MeterController {

    private final MeterService meterService;

    @PostMapping("/save")
    public MeterDto saveCounter(@Valid @RequestBody MeterDto meterDto) {
        return meterService.saveMeter(meterDto);
    }

    @PutMapping("/update/{id}")
    public MeterDto updateCounter(@Valid @RequestBody MeterDto meterDto){
        return meterService.updateMeter(meterDto);
    }

    @GetMapping("/findAll")
    public Page<MeterDto> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long equipmentId,
             @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return meterService.getAllMeters(name, equipmentId, pageable);
    }

    @GetMapping("/findByEquipment/{equipmentId}")
    public List<MeterDto> findByEquipment(@PathVariable Long equipmentId) {
        return meterService.getMetersByEquipmentId(equipmentId);
    }

    @GetMapping("/find/{id}")
    public MeterDto findById(@PathVariable Long id) {
        return meterService.getMeterById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCounter(@PathVariable Long id) {
        meterService.deleteMeter(id);
    }
}
