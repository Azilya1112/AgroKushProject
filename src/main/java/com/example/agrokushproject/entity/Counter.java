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
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="counter")
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name="counter_name")
    String counterName;
    @Column(name="description")
    String description;
    @Column(name="current_value")
    int currentValue;
    @Column(name="reading_interval")
    int readingInterval;

}
