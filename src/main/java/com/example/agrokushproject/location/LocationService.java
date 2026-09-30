package com.example.agrokushproject.location;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService {
    LocationDto saveLocation(LocationDto locationDto);
    LocationDto updateLocation(LocationDto locationDto);
    LocationDto getLocationById(Long id);
    Page<LocationDto> getAllLocations(String name, Pageable pageable);
    void deleteLocation(Long id);
}
