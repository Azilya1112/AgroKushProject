package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.MeterDto;
import com.example.agrokushproject.service.MeterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/meter")
public class MeterController {

    private final MeterService meterService;

    @PostMapping("/save")
    public MeterDto saveCounter(@RequestBody MeterDto meterDto) {
        return meterService.saveMeter(meterDto);
    }

    @PutMapping("/update/{id}")
    public MeterDto updateCounter(@RequestBody MeterDto meterDto){
        return meterService.updateMeter(meterDto);
    }

    @GetMapping("/findAll")
    public List<MeterDto> findAll() {
        return meterService.getAllMeters();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCounter(@PathVariable Long id) {
        meterService.deleteMeter(id);
    }
}
