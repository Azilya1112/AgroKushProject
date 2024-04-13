package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.SchemeDto;

import java.util.List;

public interface SchemeService {
    SchemeDto saveScheme(SchemeDto schemeDto);
    List<SchemeDto> findAllScheme();
    void deleteScheme(Long id);
}
