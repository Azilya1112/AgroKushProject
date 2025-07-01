package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.LocationDto;
import com.example.agrokushproject.entity.Location;
import com.example.agrokushproject.mapper.LocationMapper;
import com.example.agrokushproject.repositories.LocationRepository;
import com.example.agrokushproject.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    @Transactional
    public LocationDto saveLocation(LocationDto locationDto) {
        Location toSave = locationMapper.toEntity(locationDto);
        Location saved = locationRepository.save(toSave);
        return locationMapper.toDto(saved);
    }

    @Override
    @Transactional
    public LocationDto updateLocation(LocationDto locationDto) {
        Long id = (long) locationDto.getId();
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
    public List<LocationDto> getAllLocations() {
        List<Location> list = locationRepository.findAll();
        return locationMapper.toDtoList(list);
    }

    @Override
    @Transactional
    public void deleteLocation(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND,
                    "Location not found with id " + id);
        }
        locationRepository.deleteById(id);
    }
}
