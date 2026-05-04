package com.example.agrokushproject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MeterDto {
    private Long id;
    @NotBlank
    private String counterName;
    private String description;
    @Min(0)
    private int currentValue;
    @Min(1)
    private int readingInterval;
    private Long equipmentId;
}
