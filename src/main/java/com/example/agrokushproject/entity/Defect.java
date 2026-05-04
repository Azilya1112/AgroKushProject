package com.example.agrokushproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.example.agrokushproject.entity.enums.DefectStatus;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="defect")
public class Defect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="defect_name")
    private String defectName;

    @Column(name="description")
    private String description;

    @Column(name="defect_status")
    @Enumerated(EnumType.STRING)
    private DefectStatus defectStatus;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "defect",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Material> images = new ArrayList<>();
}
