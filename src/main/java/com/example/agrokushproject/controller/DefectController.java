package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.DefectDto;
import com.example.agrokushproject.service.DefectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/defect")
public class DefectController {

    private final DefectService defectService;

    @PostMapping("/save")
    public DefectDto saveDefect(@RequestBody DefectDto defectDto) {
        return defectService.saveDefect(defectDto);
    }

    @PutMapping("/update/{id}")
    public DefectDto updateDefect(@RequestBody DefectDto defectDto){
        return defectService.updateDefect(defectDto);
    }

    @GetMapping("/findAll")
    public List<DefectDto> findAll() {
        return defectService.getAllDefects();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDefect(@PathVariable Long id) {
        defectService.deleteDefect(id);
    }

}
