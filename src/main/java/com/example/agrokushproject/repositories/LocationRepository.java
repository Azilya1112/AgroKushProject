package com.example.agrokushproject.repositories;

import com.example.agrokushproject.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LocationRepository extends JpaRepository<Location, Long>, JpaSpecificationExecutor<Location> {
}
