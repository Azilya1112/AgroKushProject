package com.example.agrokushproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialDto {

    private Long id;
    @NotBlank
    private String fileName;
    @NotBlank
    private String contentType;
    private Long sizeBytes;
    // URL, по которому можно скачать файл (например, /api/materials/{id}/data)
    private String downloadUrl;
    private Long defectId;
    private Long equipmentId;
}
