package com.example.agrokushproject.repositories;

import com.example.agrokushproject.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
