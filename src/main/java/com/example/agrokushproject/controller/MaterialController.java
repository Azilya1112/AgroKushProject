package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.MaterialDto;
import com.example.agrokushproject.service.MaterialService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/material")
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping("/save")
    public MaterialDto saveMaterial(@Valid @RequestBody MaterialDto materialDto) {
        return materialService.saveMaterial(materialDto);
    }

    @PutMapping("/update/{id}")
    public MaterialDto updateMaterial(@Valid @RequestBody MaterialDto materialDto){
        return materialService.updateMaterial(materialDto);
    }

    @GetMapping("/findAll")
    public Page<MaterialDto> findAll(
            @RequestParam(required = false) String fileName,
             @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return materialService.getAllMaterials(fileName, pageable);
    }

    @GetMapping("/find/{id}")
    public MaterialDto findById(@PathVariable Long id) {
        return materialService.getMaterialById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterialById(id);
    }
}
