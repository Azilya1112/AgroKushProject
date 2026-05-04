package com.example.agrokushproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class DefectDto {

    private Long id;
    @NotBlank
    private String defectName;
    private String description;
    private Long equipmentId;
    private List<Long> imageIds;
}
