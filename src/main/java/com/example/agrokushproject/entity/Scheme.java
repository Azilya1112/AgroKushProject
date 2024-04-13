package com.example.agrokushproject.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Scheme {
    @Id
    Long Id;
    @Column(name="scheme_name")
    String schemeName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    Image schemeImage;
}
