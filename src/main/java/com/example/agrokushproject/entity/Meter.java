package com.example.agrokushproject.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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
    private int currentValue;

    @Column(name="reading_interval")
    private int readingInterval;

}
