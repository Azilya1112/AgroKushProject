package com.example.agrokushproject.controller;

import com.example.agrokushproject.dto.DefectDto;
import com.example.agrokushproject.dto.MeterDto;
import com.example.agrokushproject.service.DefectService;
import com.example.agrokushproject.service.MeterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Collections that belong to a single piece of equipment. They live here rather than on
 * MeterController/DefectController so that those keep owning exactly one collection each,
 * and so that neither of them has to depend on the other's service.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/equipment/{equipmentId}")
public class EquipmentSubResourceController {

    private final MeterService meterService;
    private final DefectService defectService;

    @GetMapping("/meters")
    public ResponseEntity<List<MeterDto>> findMeters(@PathVariable Long equipmentId) {
        return ResponseEntity.ok(meterService.getMetersByEquipmentId(equipmentId));
    }

    @GetMapping("/defects")
    public ResponseEntity<List<DefectDto>> findDefects(@PathVariable Long equipmentId) {
        return ResponseEntity.ok(defectService.getDefectsByEquipmentId(equipmentId));
    }
}
