package com.example.agrokushproject.repositories;

import com.example.agrokushproject.entity.Defect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefectRepository extends JpaRepository<Defect, Long> {
}
