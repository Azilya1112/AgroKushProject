package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.MaterialDto;
import com.example.agrokushproject.service.MaterialService;
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
@RequestMapping("/material")
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping("/save")
    public ResponseEntity<MaterialDto> save(@Valid @RequestBody MaterialDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.saveMaterial(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MaterialDto> update(@Valid @RequestBody MaterialDto dto) {
        return ResponseEntity.ok(materialService.updateMaterial(dto));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MaterialDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.getMaterialById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<MaterialDto>> findAll(
            @RequestParam(required = false) String fileName,
            @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(materialService.getAllMaterials(fileName, pageable));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        materialService.deleteMaterialById(id);
        return ResponseEntity.noContent().build();
    }
}
