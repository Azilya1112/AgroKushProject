package com.example.agrokushproject.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SparePartDto {
    private Long id;
    @NotBlank
    private String name;
    @Min(0)
    private Long quantity;
    private Set<Long> equipmentIds = new HashSet<>();
    private List<String> equipmentNames = new ArrayList<>();
}
