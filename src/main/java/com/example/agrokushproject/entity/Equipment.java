package com.example.agrokushproject.entity;

//Добавить техпаспорт оборудования
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name="equipment_name")
    String equipmentName;

    @Column(name="model")
    String model;

    @Column(name="manufacturer")
    String manufacturer;

    @Column(name="installationDate")
    LocalDateTime installationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    EquipmentStatus status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    Image techPassportImage;

}
