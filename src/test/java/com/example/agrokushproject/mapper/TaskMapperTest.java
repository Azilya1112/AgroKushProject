package com.example.agrokushproject.mapper;

import com.example.agrokushproject.dto.TaskDto;
import com.example.agrokushproject.entity.Equipment;
import com.example.agrokushproject.entity.Task;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class TaskMapperTest {

    private final TaskMapper mapper = Mappers.getMapper(TaskMapper.class);

    @Test
    void toEntity_mapsUserEquipmentAndLocationIds() {
        TaskDto dto = new TaskDto();
        dto.setName("Oil change");
        dto.setUserId(7L);
        dto.setEquipmentIds(Set.of(1L, 2L));
        dto.setLocationId(3L);

        Task task = mapper.toEntity(dto);

        assertThat(task.getUser().getId()).isEqualTo(7L);
        assertThat(task.getLocation().getId()).isEqualTo(3L);
        assertThat(task.getEquipments().stream().map(Equipment::getId).collect(Collectors.toSet()))
                .containsExactlyInAnyOrder(1L, 2L);
    }

    @Test
    void roundTrip_returnsSameIds() {
        TaskDto dto = new TaskDto();
        dto.setName("Oil change");
        dto.setUserId(7L);
        dto.setEquipmentIds(Set.of(1L, 2L));
        dto.setLocationId(3L);

        TaskDto back = mapper.toDto(mapper.toEntity(dto));

        assertThat(back.getUserId()).isEqualTo(7L);
        assertThat(back.getLocationId()).isEqualTo(3L);
        assertThat(back.getEquipmentIds()).containsExactlyInAnyOrder(1L, 2L);
    }

    @Test
    void toEntity_withoutRelations_leavesThemEmpty() {
        TaskDto dto = new TaskDto();
        dto.setName("Inspection");

        Task task = mapper.toEntity(dto);

        assertThat(task.getUser()).isNull();
        assertThat(task.getLocation()).isNull();
        assertThat(task.getEquipments()).isEmpty();
    }
}
