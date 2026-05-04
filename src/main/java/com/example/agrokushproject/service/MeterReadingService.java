package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.MeterReadingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface MeterReadingService {

    MeterReadingDto addReading(MeterReadingDto dto);

    Page<MeterReadingDto> getReadingsByMeter(Long meterId, Pageable pageable);

    Page<MeterReadingDto> getReadingsByMeterAndPeriod(Long meterId,
                                                       LocalDateTime from,
                                                       LocalDateTime to,
                                                       Pageable pageable);

    MeterReadingDto getLatestReading(Long meterId);

    double getTotalByPeriod(Long meterId, LocalDateTime from, LocalDateTime to);

    void deleteReading(Long id);
}
