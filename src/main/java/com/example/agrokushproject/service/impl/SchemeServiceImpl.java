package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.SchemeDto;
import com.example.agrokushproject.entity.Scheme;
import com.example.agrokushproject.exceptions.RecordNotFoundException;
import com.example.agrokushproject.mapper.SchemeMapper;
import com.example.agrokushproject.repositories.SchemeRepository;
import com.example.agrokushproject.service.SchemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchemeServiceImpl implements SchemeService {
    private final SchemeRepository schemeRepository;

    @Override
    public SchemeDto saveScheme(SchemeDto schemeDto) {
        Scheme scheme=SchemeMapper.INSTANCE.toEntity(schemeDto);
        try {
            Scheme schemeSave = schemeRepository.save(scheme);
            return SchemeMapper.INSTANCE.toDto(schemeSave);
        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось сохранить в базе!", e);
        }
    }

    @Override
    public List<SchemeDto> findAllScheme() {
        return SchemeMapper.INSTANCE.toResponseList(schemeRepository.findAll());
    }

    @Override
    public void deleteScheme(Long id) {
        Scheme scheme=this.schemeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Статус оборудования не найден"));
        schemeRepository.deleteById(id);

    }


}
