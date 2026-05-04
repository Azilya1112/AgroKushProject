package com.example.agrokushproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="meter")
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="counter_name")
    private String counterName;

    @Column(name="description")
    private String description;

    @Column(name="current_value")
    private double currentValue;

    @Column(name="reading_interval")
    private int readingInterval;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "meter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MeterReading> readings = new ArrayList<>();
}
