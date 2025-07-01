package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.SparePartDto;
import com.example.agrokushproject.entity.SparePart;
import com.example.agrokushproject.exceptions.RecordNotFoundException;
import com.example.agrokushproject.mapper.SparePartMapper;
import com.example.agrokushproject.repositories.SparePartRepository;
import com.example.agrokushproject.service.SparePartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SparePartServiceImpl implements SparePartService {

    private final SparePartRepository sparePartRepository;
    private final SparePartMapper sparePartMapper;

    @Override
    @Transactional
    public SparePartDto saveSparePart(SparePartDto sparePartDto) {
        SparePart entity = sparePartMapper.toEntity(sparePartDto);
        SparePart saved = sparePartRepository.save(entity);
        return sparePartMapper.toDto(saved);
    }

    @Override
    @Transactional
    public SparePartDto updateSparePart(SparePartDto sparePartDto) {
        Long id = sparePartDto.getId();
        if (id == null) {
            throw new ResponseStatusException(NOT_FOUND, "SparePart id must be provided for update");
        }
        SparePart existing = sparePartRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "SparePart not found with id " + id));

        SparePart toSave = sparePartMapper.toEntity(sparePartDto);
        toSave.setId(existing.getId());

        SparePart updated = sparePartRepository.save(toSave);
        return sparePartMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SparePartDto> getAllSparePart() {
        List<SparePart> list = sparePartRepository.findAll();
        return sparePartMapper.toDtoList(list);
    }

    @Override
    @Transactional
    public void deleteSparePart(Long id) {
        if (!sparePartRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "SparePart not found with id " + id);
        }
        sparePartRepository.deleteById(id);
    }
}