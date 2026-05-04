package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.MeterReadingDto;
import com.example.agrokushproject.entity.Meter;
import com.example.agrokushproject.entity.MeterReading;
import com.example.agrokushproject.mapper.MeterReadingMapper;
import com.example.agrokushproject.repositories.MeterReadingRepository;
import com.example.agrokushproject.repositories.MeterRepository;
import com.example.agrokushproject.service.MeterReadingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeterReadingServiceImpl implements MeterReadingService {

    private final MeterReadingRepository readingRepository;
    private final MeterReadingMapper readingMapper;
    private final MeterRepository meterRepository;

    @Override
    @Transactional
    public MeterReadingDto addReading(MeterReadingDto dto) {
        Meter meter = meterRepository.findById(dto.getMeterId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Meter not found with id: " + dto.getMeterId()));

        if (dto.getRecordedAt() == null) {
            dto.setRecordedAt(LocalDateTime.now());
        }

        MeterReading reading = readingMapper.toEntity(dto);
        reading.setMeter(meter);

        // обновляем currentValue в самом счётчике
        meter.setCurrentValue(dto.getValue());
        meterRepository.save(meter);

        MeterReading saved = readingRepository.save(reading);
        log.info("Added reading {} for meter id={}", dto.getValue(), dto.getMeterId());
        return readingMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MeterReadingDto> getReadingsByMeter(Long meterId, Pageable pageable) {
        if (!meterRepository.existsById(meterId)) {
            throw new ResponseStatusException(NOT_FOUND, "Meter not found with id: " + meterId);
        }
        return readingRepository
                .findByMeterIdOrderByRecordedAtDesc(meterId, pageable)
                .map(readingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MeterReadingDto> getReadingsByMeterAndPeriod(Long meterId,
                                                              LocalDateTime from,
                                                              LocalDateTime to,
                                                              Pageable pageable) {
        if (!meterRepository.existsById(meterId)) {
            throw new ResponseStatusException(NOT_FOUND, "Meter not found with id: " + meterId);
        }
        return readingRepository
                .findByMeterIdAndRecordedAtBetweenOrderByRecordedAtDesc(meterId, from, to, pageable)
                .map(readingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public MeterReadingDto getLatestReading(Long meterId) {
        return readingRepository.findTopByMeterIdOrderByRecordedAtDesc(meterId)
                .map(readingMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "No readings found for meter id: " + meterId));
    }

    @Override
    @Transactional(readOnly = true)
    public double getTotalByPeriod(Long meterId, LocalDateTime from, LocalDateTime to) {
        if (!meterRepository.existsById(meterId)) {
            throw new ResponseStatusException(NOT_FOUND, "Meter not found with id: " + meterId);
        }
        return readingRepository.sumByMeterIdAndPeriod(meterId, from, to);
    }

    @Override
    @Transactional
    public void deleteReading(Long id) {
        if (!readingRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Reading not found with id: " + id);
        }
        readingRepository.deleteById(id);
        log.info("Deleted reading id={}", id);
    }
}
