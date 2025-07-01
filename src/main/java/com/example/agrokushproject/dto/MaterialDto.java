package com.example.agrokushproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialDto {

    private Long id;
    private String fileName;
    private String contentType;
    private Long sizeBytes;
    // URL, по которому можно скачать файл (например, /api/materials/{id}/data)
    private String downloadUrl;
    private Long defectId;
    private Long equipmentId;
}
