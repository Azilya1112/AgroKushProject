package com.example.agrokushproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="image_name")
    private String fileName;

    @Column(name="content_type")
    private String contentType;

    @Lob
    @Column(name = "image")
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="defect_id")
    private Defect defect;


    @OneToOne(mappedBy = "techPassport")
    private Equipment equipment;
}
