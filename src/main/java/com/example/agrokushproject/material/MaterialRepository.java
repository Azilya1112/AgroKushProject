package com.example.agrokushproject.material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaterialRepository extends JpaRepository<Material, Long>, JpaSpecificationExecutor<Material> {
}
