package com.example.agrokushproject.location;

import com.example.agrokushproject.equipment.Equipment;
import com.example.agrokushproject.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name= "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="address")
    private String address;

    @Column(name="coordinates")
    private String coordinates;

    @OneToMany(mappedBy = "location")
    private List<Equipment> equipments = new ArrayList<>();

    @OneToMany(mappedBy = "location")
    private List<Task> tasks = new ArrayList<>();
}