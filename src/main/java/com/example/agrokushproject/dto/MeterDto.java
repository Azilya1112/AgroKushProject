package com.example.agrokushproject.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MeterDto {
    private Long id;
    private String counterName;
    private String description;
    private int currentValue;
    private int readingInterval;

}
