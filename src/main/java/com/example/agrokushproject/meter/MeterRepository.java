package com.example.agrokushproject.meter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MeterRepository extends JpaRepository<Meter, Long>, JpaSpecificationExecutor<Meter> {
    List<Meter> findByEquipmentId(Long equipmentId);
}
