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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<EquipmentDto> save(@Valid @RequestBody EquipmentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentService.saveEquipment(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentDto> update(@PathVariable Long id, @Valid @RequestBody EquipmentDto dto) {
        dto.setId(id);
        return ResponseEntity.ok(equipmentService.updateEquipment(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(equipmentService.getEquipmentById(id));
    }

    @GetMapping
    public ResponseEntity<Page<EquipmentDto>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) EquipmentStatus status,
            @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(equipmentService.getAllEquipment(name, status, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }
}
