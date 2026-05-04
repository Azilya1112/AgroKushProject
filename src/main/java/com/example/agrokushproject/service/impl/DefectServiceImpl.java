package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.DefectDto;
import com.example.agrokushproject.entity.Defect;
import com.example.agrokushproject.entity.enums.DefectStatus;
import com.example.agrokushproject.mapper.DefectMapper;
import com.example.agrokushproject.repositories.DefectRepository;
import com.example.agrokushproject.service.DefectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Slf4j
@Service
@RequiredArgsConstructor
public class DefectServiceImpl implements DefectService {

    private final DefectRepository defectRepository;
    private final DefectMapper defectMapper;

    @Override
    @Transactional
    public DefectDto saveDefect(DefectDto defectDto) {
        log.info("Saving defect: {}", defectDto.getDefectName());
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
        log.info("Updating defect with id: {}", id);
        Defect existing = defectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Defect not found with id " + id));

        Defect toSave = defectMapper.toEntity(defectDto);
        toSave.setId(existing.getId());

        Defect updated = defectRepository.save(toSave);
        return defectMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DefectDto> getAllDefects(String name, DefectStatus defectStatus, Long equipmentId, Pageable pageable) {
        Specification<Defect> spec = (root, q, cb) -> cb.conjunction();
        if (name != null && !name.isBlank()) {
            spec = spec.and((root, q, cb) ->
                cb.like(cb.lower(root.get("defectName")), "%" + name.toLowerCase() + "%"));
        }
        if (defectStatus != null) {
            spec = spec.and((root, q, cb) ->
                cb.equal(root.get("defectStatus"), defectStatus));
        }
        if (equipmentId != null) {
            spec = spec.and((root, q, cb) ->
                cb.equal(root.get("equipment").get("id"), equipmentId));
        }
        return defectRepository.findAll(spec, pageable).map(defectMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DefectDto> getDefectsByDefectId(Long id) {
        return defectRepository.findById(id)
                .map(d -> List.of(defectMapper.toDto(d)))
                .orElse(Collections.emptyList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DefectDto> getDefectsByEquipmentId(Long equipmentId) {
        log.debug("Fetching defects for equipment id: {}", equipmentId);
        List<Defect> list = defectRepository.findByEquipmentId(equipmentId);
        return list.isEmpty() ? Collections.emptyList() : defectMapper.toDtoList(list);
    }

    @Override
    @Transactional
    public void deleteDefect(Long id) {
        if (!defectRepository.existsById(id)) {
            log.warn("Defect not found with id: {}", id);
            throw new ResponseStatusException(NOT_FOUND, "Defect not found with id " + id);
        }
        log.info("Deleting defect with id: {}", id);
        defectRepository.deleteById(id);
    }

    @Override
    public DefectDto getDefectById(Long id) {
        Defect defect = defectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Defect not found with id " + id));
        return defectMapper.toDto(defect);
    }

    @Override
    @Transactional
    public DefectDto updateDefectStatus(Long id, DefectStatus status) {
        Defect defect = defectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Defect not found with id " + id));
        defect.setDefectStatus(status);
        return defectMapper.toDto(defectRepository.save(defect));
    }
}

