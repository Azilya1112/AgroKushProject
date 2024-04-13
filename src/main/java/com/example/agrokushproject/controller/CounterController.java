package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.CounterDto;
import com.example.agrokushproject.service.CounterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/counter")
public class CounterController {

    private final CounterService counterService;

    @PostMapping("/save")
    public CounterDto saveCounter(@RequestBody CounterDto counterDto) {
        return counterService.saveCounter(counterDto);
    }

    @PutMapping("/update/{id}")
    public CounterDto updateCounter(@RequestBody CounterDto counterDto){
        return counterService.updateCounter(counterDto);
    }

    @GetMapping("/findAll")
    public List<CounterDto> findAll() {
        return counterService.findAllCounter();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCounter(@PathVariable Long id) {
        counterService.deleteCounter(id);
    }
}
