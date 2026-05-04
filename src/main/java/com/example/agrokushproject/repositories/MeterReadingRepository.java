package com.example.agrokushproject.repositories;

import com.example.agrokushproject.entity.MeterReading;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MeterReadingRepository extends JpaRepository<MeterReading, Long> {

    Page<MeterReading> findByMeterIdOrderByRecordedAtDesc(Long meterId, Pageable pageable);

    Page<MeterReading> findByMeterIdAndRecordedAtBetweenOrderByRecordedAtDesc(
            Long meterId, LocalDateTime from, LocalDateTime to, Pageable pageable);

    Optional<MeterReading> findTopByMeterIdOrderByRecordedAtDesc(Long meterId);

    @Query("SELECT COALESCE(MAX(r.value) - MIN(r.value), 0) FROM MeterReading r " +
           "WHERE r.meter.id = :meterId AND r.recordedAt BETWEEN :from AND :to")
    double sumByMeterIdAndPeriod(@Param("meterId") Long meterId,
                                 @Param("from") LocalDateTime from,
                                 @Param("to") LocalDateTime to);
}
