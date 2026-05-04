package com.example.agrokushproject.repositories;

import com.example.agrokushproject.entity.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartRepository extends JpaRepository<SparePart, Long>, JpaSpecificationExecutor<SparePart> {
}
