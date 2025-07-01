package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.MeterDto;
import com.example.agrokushproject.entity.Meter;
import com.example.agrokushproject.exceptions.RecordNotFoundException;
import com.example.agrokushproject.mapper.MeterMapper;
import com.example.agrokushproject.repositories.MeterRepository;
import com.example.agrokushproject.service.MeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MeterServiceImpl implements MeterService {

    private final MeterRepository meterRepository;
    private final MeterMapper meterMapper;

    @Override
    @Transactional
    public MeterDto saveMeter(MeterDto meterDto) {
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

        Meter existing = meterRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Meter not found with id: " + id));

        Meter updated=meterMapper.toEntity(meterDto);
        updated.setId(existing.getId());

        Meter saved = meterRepository.save(updated);
        return meterMapper.toDto(saved);

    }

    @Override
    @Transactional
    public List<MeterDto> getAllMeters() {
        List<Meter> meters = meterRepository.findAll();
        return meterMapper.toDtoList(meters);
    }

    @Override
    @Transactional
    public void deleteMeter(Long id) {
        if(!meterRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Meter not found with id: " + id);
        }
        meterRepository.deleteById(id);

    }
}
