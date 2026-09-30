package com.example.agrokushproject.defect;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DefectRepository extends JpaRepository<Defect, Long>, JpaSpecificationExecutor<Defect> {
    List<Defect> findByEquipmentId(Long equipmentId);
}
