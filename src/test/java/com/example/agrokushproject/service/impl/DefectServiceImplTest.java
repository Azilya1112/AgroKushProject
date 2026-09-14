package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.DefectDto;
import com.example.agrokushproject.entity.Defect;
import com.example.agrokushproject.entity.enums.DefectStatus;
import com.example.agrokushproject.mapper.DefectMapper;
import org.mapstruct.factory.Mappers;
import com.example.agrokushproject.repositories.DefectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefectServiceImplTest {

    @Mock
    private DefectRepository defectRepository;

    private final DefectMapper defectMapper = Mappers.getMapper(DefectMapper.class);

    private DefectServiceImpl defectService;

    @BeforeEach
    void setUp() {
        defectService = new DefectServiceImpl(defectRepository, defectMapper);
        when(defectRepository.save(any(Defect.class))).thenAnswer(inv -> inv.getArgument(0));
    }

    @Test
    void saveDefect_alwaysStartsOpen_evenIfClientSendsAnotherStatus() {
        DefectDto dto = new DefectDto();
        dto.setDefectName("Leaking hydraulics");
        dto.setDefectStatus(DefectStatus.CLOSED);

        DefectDto saved = defectService.saveDefect(dto);

        assertThat(saved.getDefectStatus()).isEqualTo(DefectStatus.OPEN);
    }

    @Test
    void updateDefect_keepsExistingStatus() {
        Defect existing = new Defect();
        existing.setId(5L);
        existing.setDefectName("Leaking hydraulics");
        existing.setDefectStatus(DefectStatus.IN_PROGRESS);
        when(defectRepository.findById(5L)).thenReturn(Optional.of(existing));

        DefectDto dto = new DefectDto();
        dto.setId(5L);
        dto.setDefectName("Leaking hydraulics, rear");
        dto.setDefectStatus(null);

        DefectDto updated = defectService.updateDefect(dto);

        ArgumentCaptor<Defect> captor = ArgumentCaptor.forClass(Defect.class);
        verify(defectRepository).save(captor.capture());
        assertThat(captor.getValue().getDefectStatus()).isEqualTo(DefectStatus.IN_PROGRESS);
        assertThat(updated.getDefectStatus()).isEqualTo(DefectStatus.IN_PROGRESS);
        assertThat(updated.getDefectName()).isEqualTo("Leaking hydraulics, rear");
    }
}