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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/defects")
public class DefectController {

    private final DefectService defectService;

    @PostMapping
    public ResponseEntity<DefectDto> save(@Valid @RequestBody DefectDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(defectService.saveDefect(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefectDto> update(@PathVariable Long id, @Valid @RequestBody DefectDto dto) {
        dto.setId(id);
        return ResponseEntity.ok(defectService.updateDefect(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefectDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(defectService.getDefectById(id));
    }

    @GetMapping
    public ResponseEntity<Page<DefectDto>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long equipmentId,
            @RequestParam(required = false) DefectStatus defectStatus,
            @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(defectService.getAllDefects(name, defectStatus, equipmentId, pageable));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DefectDto> updateStatus(@PathVariable Long id, @RequestParam DefectStatus status) {
        return ResponseEntity.ok(defectService.updateDefectStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        defectService.deleteDefect(id);
        return ResponseEntity.noContent().build();
    }
}
