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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/meters/{meterId}/readings")
public class MeterReadingController {

    private final MeterReadingService readingService;

    @PostMapping
    public ResponseEntity<MeterReadingDto> addReading(
            @PathVariable Long meterId,
            @Valid @RequestBody MeterReadingDto dto) {
        dto.setMeterId(meterId);
        return ResponseEntity.status(HttpStatus.CREATED).body(readingService.addReading(dto));
    }

    @GetMapping
    public ResponseEntity<Page<MeterReadingDto>> getReadings(
            @PathVariable Long meterId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        if (from != null && to != null) {
            return ResponseEntity.ok(readingService.getReadingsByMeterAndPeriod(meterId, from, to, pageable));
        }
        return ResponseEntity.ok(readingService.getReadingsByMeter(meterId, pageable));
    }

    @GetMapping("/latest")
    public ResponseEntity<MeterReadingDto> getLatest(@PathVariable Long meterId) {
        return ResponseEntity.ok(readingService.getLatestReading(meterId));
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getTotal(
            @PathVariable Long meterId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ResponseEntity.ok(readingService.getTotalByPeriod(meterId, from, to));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReading(
            @PathVariable Long meterId,
            @PathVariable Long id) {
        readingService.deleteReading(id);
        return ResponseEntity.noContent().build();
    }
}
