package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.LocationDto;

import java.util.List;

public interface LocationService {

    LocationDto saveLocation(LocationDto locationDto);
    LocationDto updateLocation(LocationDto locationDto);
    List<LocationDto> getAllLocations();
    void deleteLocation(Long id);
}
