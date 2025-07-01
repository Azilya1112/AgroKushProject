package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.DefectDto;
import com.example.agrokushproject.entity.Defect;
import com.example.agrokushproject.mapper.DefectMapper;
import com.example.agrokushproject.repositories.DefectRepository;
import com.example.agrokushproject.service.DefectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
@RequiredArgsConstructor
public class DefectServiceImpl implements DefectService {

    private final DefectRepository defectRepository;
    private final DefectMapper defectMapper;

    @Override
    @Transactional
    public DefectDto saveDefect(DefectDto defectDto) {
        Defect entity = defectMapper.toEntity(defectDto);
        Defect saved = defectRepository.save(entity);
        return defectMapper.toDto(saved);
    }

    @Override
    @Transactional
    public DefectDto updateDefect(DefectDto defectDto) {
        Long id = defectDto.getId();
        if (id == null) {
            throw new ResponseStatusException(NOT_FOUND, "Defect id must be provided for update");
        }
        Defect existing = defectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Defect not found with id " + id));

        Defect toSave = defectMapper.toEntity(defectDto);
        toSave.setId(existing.getId());

        Defect updated = defectRepository.save(toSave);
        return defectMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DefectDto> getAllDefects() {
        List<Defect> list = defectRepository.findAll();
        return list.isEmpty()
                ? Collections.emptyList()
                : defectMapper.toDtoList(list);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DefectDto> getDefectsByDefectId(Long id) {
        return defectRepository.findById(id)
                .map(d -> List.of(defectMapper.toDto(d)))
                .orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public void deleteDefect(Long id) {
        if (!defectRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Defect not found with id " + id);
        }
        defectRepository.deleteById(id);
    }
}

