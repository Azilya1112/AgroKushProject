package com.example.agrokushproject.dto;

import com.example.agrokushproject.entity.TaskStatus;
//import com.example.agrokushproject.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDto {
    Long id;
    String name;
    String description;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Long taskStatusId;
    Long userId;
}
