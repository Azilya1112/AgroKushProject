package com.example.agrokushproject.dto;


import com.example.agrokushproject.entity.enums.EquipmentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentDto {
    private Long id;
    @NotBlank
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
