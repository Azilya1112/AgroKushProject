package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.MeterDto;
import com.example.agrokushproject.service.MeterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/meter")
public class MeterController {

    private final MeterService meterService;

    @PostMapping("/save")
    public ResponseEntity<MeterDto> save(@Valid @RequestBody MeterDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(meterService.saveMeter(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MeterDto> update(@Valid @RequestBody MeterDto dto) {
        return ResponseEntity.ok(meterService.updateMeter(dto));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MeterDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(meterService.getMeterById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<MeterDto>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long equipmentId,
            @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(meterService.getAllMeters(name, equipmentId, pageable));
    }

    @GetMapping("/findByEquipment/{equipmentId}")
    public ResponseEntity<List<MeterDto>> findByEquipment(@PathVariable Long equipmentId) {
        return ResponseEntity.ok(meterService.getMetersByEquipmentId(equipmentId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        meterService.deleteMeter(id);
        return ResponseEntity.noContent().build();
    }
}
