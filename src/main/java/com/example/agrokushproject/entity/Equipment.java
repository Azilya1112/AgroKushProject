package com.example.agrokushproject.entity;

import com.example.agrokushproject.entity.enums.EquipmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="equipment_name")
    private String equipmentName;

    @Column(name="model")
    private String model;

    @Column(name="manufacturer")
    private String manufacturer;

    @Column(name="installation_date")
    private LocalDateTime installationDate;

    @Column(name="equipment_status")
    @Enumerated(EnumType.STRING)
    private EquipmentStatus equipmentStatus;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "equipments")
    private Set<Task> tasks = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name = "equipment_spare_parts",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "spare_part_id")
    )
    private Set<SparePart> spareParts = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_passport_id")
    private Material techPassport;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Defect> defects = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Meter> meters = new ArrayList<>();
}
