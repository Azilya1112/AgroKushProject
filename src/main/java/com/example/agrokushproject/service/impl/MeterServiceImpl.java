package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.MeterDto;
import com.example.agrokushproject.entity.Meter;
import com.example.agrokushproject.mapper.MeterMapper;
import com.example.agrokushproject.repositories.MeterRepository;
import com.example.agrokushproject.service.MeterService;

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
public class MeterServiceImpl implements MeterService {

    private final MeterRepository meterRepository;
    private final MeterMapper meterMapper;

    @Override
    @Transactional
    public MeterDto saveMeter(MeterDto meterDto) {
        log.info("Saving meter: {}", meterDto.getCounterName());
        Meter toSave = meterMapper.toEntity(meterDto);
        Meter saved = meterRepository.save(toSave);
        return meterMapper.toDto(saved);
    }

    @Override
    @Transactional
    public MeterDto updateMeter(MeterDto meterDto) {
        Long id = meterDto.getId();
        if(id == null) {
            throw new ResponseStatusException(NOT_FOUND, "Meter id must be provided for update");
        }
        log.info("Updating meter with id: {}", id);
        Meter existing = meterRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Meter not found with id: " + id));

        Meter updated=meterMapper.toEntity(meterDto);
        updated.setId(existing.getId());

        Meter saved = meterRepository.save(updated);
        return meterMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MeterDto> getAllMeters(String name, Long equipmentId, Pageable pageable) {
        log.debug("Fetching meters, name={}, equipmentId={}", name, equipmentId);
        Specification<Meter> spec = (root, q, cb) -> cb.conjunction();
        if (name != null && !name.isBlank()) {
            spec = spec.and((root, q, cb) ->
                cb.like(cb.lower(root.get("counterName")), "%" + name.toLowerCase() + "%"));
        }
        if (equipmentId != null) {
            spec = spec.and((root, q, cb) ->
                cb.equal(root.get("equipment").get("id"), equipmentId));
        }
        return meterRepository.findAll(spec, pageable).map(meterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MeterDto> getMetersByEquipmentId(Long equipmentId) {
        log.debug("Fetching meters for equipment id: {}", equipmentId);
        List<Meter> meters = meterRepository.findByEquipmentId(equipmentId);
        return meters.isEmpty() ? Collections.emptyList() : meterMapper.toDtoList(meters);
    }

    @Override
    @Transactional
    public void deleteMeter(Long id) {
        if(!meterRepository.existsById(id)) {
            log.warn("Meter not found with id: {}", id);
            throw new ResponseStatusException(NOT_FOUND, "Meter not found with id: " + id);
        }
        log.info("Deleting meter with id: {}", id);
        meterRepository.deleteById(id);
    }

    @Override
    public MeterDto getMeterById(Long id) {
        Meter meter = meterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Meter not found with id: " + id));
        return meterMapper.toDto(meter);
    }
}
