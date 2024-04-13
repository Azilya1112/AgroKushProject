package com.example.agrokushproject.repositories;

import com.example.agrokushproject.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Counter, Long> {
}
