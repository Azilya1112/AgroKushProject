package com.example.agrokushproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class DefectDto {

    private Long id;
    private String defectName;
    private String description;
    private List<Long> imageIds;
}
