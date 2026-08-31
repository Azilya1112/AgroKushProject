package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.LocationDto;
import com.example.agrokushproject.service.LocationService;
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
@RequestMapping("/api/v1/locations")
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationDto> save(@Valid @RequestBody LocationDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.saveLocation(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDto> update(@PathVariable Long id, @Valid @RequestBody LocationDto dto) {
        dto.setId(id);
        return ResponseEntity.ok(locationService.updateLocation(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @GetMapping
    public ResponseEntity<Page<LocationDto>> findAll(
            @RequestParam(required = false) String name,
            @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(locationService.getAllLocations(name, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
