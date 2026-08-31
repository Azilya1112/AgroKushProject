package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.SparePartDto;
import com.example.agrokushproject.service.SparePartService;
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
@RequestMapping("/api/v1/spare-parts")
public class SparePartController {

    private final SparePartService sparePartService;

    @PostMapping
    public ResponseEntity<SparePartDto> save(@Valid @RequestBody SparePartDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sparePartService.saveSparePart(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SparePartDto> update(@PathVariable Long id, @Valid @RequestBody SparePartDto dto) {
        dto.setId(id);
        return ResponseEntity.ok(sparePartService.updateSparePart(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SparePartDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(sparePartService.getSparePartById(id));
    }

    @GetMapping
    public ResponseEntity<Page<SparePartDto>> findAll(
            @RequestParam(required = false) String name,
            @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(sparePartService.getAllSparePart(name, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sparePartService.deleteSparePart(id);
        return ResponseEntity.noContent().build();
    }
}
