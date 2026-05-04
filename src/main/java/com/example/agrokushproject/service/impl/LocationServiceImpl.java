package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.LocationDto;
import com.example.agrokushproject.entity.Location;
import com.example.agrokushproject.mapper.LocationMapper;
import com.example.agrokushproject.repositories.LocationRepository;
import com.example.agrokushproject.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    @Transactional
    public LocationDto saveLocation(LocationDto locationDto) {
        log.info("Saving location: {}", locationDto.getName());
        Location toSave = locationMapper.toEntity(locationDto);
        Location saved = locationRepository.save(toSave);
        return locationMapper.toDto(saved);
    }

    @Override
    @Transactional
    public LocationDto updateLocation(LocationDto locationDto) {
        Long id = locationDto.getId();
        log.info("Updating location with id: {}", id);
        Location existing = locationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Location not found with id " + id));

        Location toSave = locationMapper.toEntity(locationDto);
        toSave.setId(existing.getId());

        Location updated = locationRepository.save(toSave);
        return locationMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LocationDto> getAllLocations(String name, Pageable pageable) {
        log.debug("Fetching locations, name={}", name);
        Specification<Location> spec = (root, q, cb) -> cb.conjunction();
        if (name != null && !name.isBlank()) {
            spec = spec.and((root, q, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }
        return locationRepository.findAll(spec, pageable).map(locationMapper::toDto);
    }

    @Override
    @Transactional
    public void deleteLocation(Long id) {
        if (!locationRepository.existsById(id)) {
            log.warn("Location not found with id: {}", id);
            throw new ResponseStatusException(NOT_FOUND,
                    "Location not found with id " + id);
        }
        log.info("Deleting location with id: {}", id);
        locationRepository.deleteById(id);
    }

    @Override
    public LocationDto getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Location not found with id " + id));
        return locationMapper.toDto(location);
    }
}
