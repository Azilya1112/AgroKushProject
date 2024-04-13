package com.example.agrokushproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name="image_name")
    String imageName;
    @Column(name="type")
    String type;
    @Lob
    @Column(name = "image")
    private byte[] image;
}
