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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageDto {

    @JsonProperty("imageName")
    String imageName;

    @JsonProperty("type")
    private String type;

    @JsonProperty("image")
    private byte[] image;

    @JsonProperty("imageExtension")
    private String imageExtension;
}
