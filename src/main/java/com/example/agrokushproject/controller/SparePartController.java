package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.SparePartDto;
import com.example.agrokushproject.service.SparePartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sparePart")
public class SparePartController {

    private final SparePartService sparePartService;

    @PostMapping("/save")
    public SparePartDto saveSparePart(@RequestBody SparePartDto sparePartDto) {
        return sparePartService.saveSparePart(sparePartDto);
    }

    @PutMapping("/update/{id}")
    public SparePartDto updateSparePart(@RequestBody SparePartDto sparePartDto){
        return sparePartService.updateSparePart(sparePartDto);
    }

    @GetMapping("/findAll")
    public List<SparePartDto> findAll() {
        return sparePartService.getAllSparePart();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSparePart(@PathVariable Long id) {
        sparePartService.deleteSparePart(id);
    }
}
