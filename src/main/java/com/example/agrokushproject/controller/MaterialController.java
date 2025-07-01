package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.MaterialDto;
import com.example.agrokushproject.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/material")
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping("/save")
    public MaterialDto saveMaterial(@RequestBody MaterialDto materialDto) {
        return materialService.saveMaterial(materialDto);
    }

    @PutMapping("/update/{id}")
    public MaterialDto updateMaterial(@RequestBody MaterialDto materialDto){
        return materialService.updateMaterial(materialDto);
    }

    @GetMapping("/findAll")
    public List<MaterialDto> findAll() {
        return materialService.getAllMaterials();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterialById(id);
    }
}
