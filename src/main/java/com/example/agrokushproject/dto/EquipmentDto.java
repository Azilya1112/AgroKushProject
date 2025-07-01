package com.example.agrokushproject.dto;


import com.example.agrokushproject.entity.enums.EquipmentStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentDto {
    private Long id;
    private String equipmentName;
    private String model;
    private String manufacturer;
    private LocalDateTime installationDate;
    private EquipmentStatus equipmentStatus;
    private Set<Long> taskIds;
    private Set<Long> sparePartIds;
    private Long locationId;
    private Long techPassportId;
}
