package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.SparePartDto;
import com.example.agrokushproject.service.SparePartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/sparePart")
public class SparePartController {

    private final SparePartService sparePartService;

    @PostMapping("/save")
    public SparePartDto saveSparePart(@Valid @RequestBody SparePartDto sparePartDto) {
        return sparePartService.saveSparePart(sparePartDto);
    }

    @PutMapping("/update/{id}")
    public SparePartDto updateSparePart(@Valid @RequestBody SparePartDto sparePartDto){
        return sparePartService.updateSparePart(sparePartDto);
    }

    @GetMapping("/findAll")
    public Page<SparePartDto> findAll(
            @RequestParam(required = false) String name,
             @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return sparePartService.getAllSparePart(name, pageable);
    }

    @GetMapping("/find/{id}")
    public SparePartDto findById(@PathVariable Long id) {
        return sparePartService.getSparePartById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSparePart(@PathVariable Long id) {
        sparePartService.deleteSparePart(id);
    }
}
