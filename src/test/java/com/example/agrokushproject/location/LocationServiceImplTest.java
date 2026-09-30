package com.example.agrokushproject.location;

import com.example.agrokushproject.equipment.EquipmentRepository;
import com.example.agrokushproject.task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

    @Mock
    private LocationRepository locationRepository;
    @Mock
    private EquipmentRepository equipmentRepository;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private LocationMapper locationMapper;

    @InjectMocks
    private LocationServiceImpl locationService;

    @Test
    void deleteLocation_withEquipmentAttached_throwsConflictAndDeletesNothing() {
        when(locationRepository.existsById(1L)).thenReturn(true);
        when(equipmentRepository.countByLocationId(1L)).thenReturn(2L);
        when(taskRepository.countByLocationId(1L)).thenReturn(0L);

        assertThatThrownBy(() -> locationService.deleteLocation(1L))
                .isInstanceOfSatisfying(ResponseStatusException.class, ex -> {
                    assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
                    assertThat(ex.getReason()).contains("2 equipment");
                });
        verify(locationRepository, never()).deleteById(anyLong());
    }

    @Test
    void deleteLocation_withTasksAttached_throwsConflict() {
        when(locationRepository.existsById(1L)).thenReturn(true);
        when(equipmentRepository.countByLocationId(1L)).thenReturn(0L);
        when(taskRepository.countByLocationId(1L)).thenReturn(3L);

        assertThatThrownBy(() -> locationService.deleteLocation(1L))
                .isInstanceOfSatisfying(ResponseStatusException.class,
                        ex -> assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.CONFLICT));
        verify(locationRepository, never()).deleteById(anyLong());
    }

    @Test
    void deleteLocation_empty_deletes() {
        when(locationRepository.existsById(1L)).thenReturn(true);
        when(equipmentRepository.countByLocationId(1L)).thenReturn(0L);
        when(taskRepository.countByLocationId(1L)).thenReturn(0L);

        locationService.deleteLocation(1L);

        verify(locationRepository).deleteById(1L);
    }

    @Test
    void deleteLocation_missing_throwsNotFound() {
        when(locationRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> locationService.deleteLocation(1L))
                .isInstanceOfSatisfying(ResponseStatusException.class,
                        ex -> assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND));
        verify(locationRepository, never()).deleteById(anyLong());
    }
}
