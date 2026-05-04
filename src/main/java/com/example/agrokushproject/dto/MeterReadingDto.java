package com.example.agrokushproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MeterReadingDto {

    private Long id;

    @NotNull
    private Long meterId;

    @NotNull
    private Double value;

    @PastOrPresent
    private LocalDateTime recordedAt;

    private String notes;
}
