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

    // Not @NotNull: the meter is identified by the path (/meters/{meterId}/readings) and the
    // controller overwrites this field from it. Body validation runs before that, so requiring
    // it here would reject a correctly-shaped request. The service still 404s on a missing meter.
    private Long meterId;

    @NotNull
    private Double value;

    @PastOrPresent
    private LocalDateTime recordedAt;

    private String notes;
}
