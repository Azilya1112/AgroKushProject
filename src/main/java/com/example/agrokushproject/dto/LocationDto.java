package com.example.agrokushproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationDto {
    private int id;
    private String name;
    private String description;
    private String address;
    private String coordinates;
    private List<Long> equipmentsId = new ArrayList<>();
    private List<Long> tasksId = new ArrayList<>();
}
