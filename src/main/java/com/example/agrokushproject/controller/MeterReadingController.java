package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.MeterReadingDto;
import com.example.agrokushproject.service.MeterReadingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/meter/{meterId}/readings")
public class MeterReadingController {

    private final MeterReadingService readingService;

    @PostMapping
    public MeterReadingDto addReading(
            @PathVariable Long meterId,
            @Valid @RequestBody MeterReadingDto dto) {
        dto.setMeterId(meterId);
        return readingService.addReading(dto);
    }

    @GetMapping
    public Page<MeterReadingDto> getReadings(
            @PathVariable Long meterId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
             @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        if (from != null && to != null) {
            return readingService.getReadingsByMeterAndPeriod(meterId, from, to, pageable);
        }
        return readingService.getReadingsByMeter(meterId, pageable);
    }

    @GetMapping("/latest")
    public MeterReadingDto getLatest(@PathVariable Long meterId) {
        return readingService.getLatestReading(meterId);
    }

    @GetMapping("/total")
    public double getTotal(
            @PathVariable Long meterId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return readingService.getTotalByPeriod(meterId, from, to);
    }

    @DeleteMapping("/{id}")
    public void deleteReading(
            @PathVariable Long meterId,
            @PathVariable Long id) {
        readingService.deleteReading(id);
    }
}
