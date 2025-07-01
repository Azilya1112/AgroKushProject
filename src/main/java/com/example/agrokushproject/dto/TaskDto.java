package com.example.agrokushproject.dto;

//import com.example.agrokushproject.entity.User;
import com.example.agrokushproject.entity.enums.TaskStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TaskStatus taskStatus;
    private Long userId;
    private Set<Long> equipmentIds;
    private Long locationId;
}
