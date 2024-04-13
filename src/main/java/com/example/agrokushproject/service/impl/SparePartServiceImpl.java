package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.SparePartDto;
import com.example.agrokushproject.entity.SparePart;
import com.example.agrokushproject.exceptions.RecordNotFoundException;
import com.example.agrokushproject.mapper.SparePartMapper;
import com.example.agrokushproject.repositories.SparePartRepository;
import com.example.agrokushproject.service.SparePartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SparePartServiceImpl implements SparePartService {

    private final SparePartRepository sparePartRepository;


    @Override
    public SparePartDto saveSparePart(SparePartDto sparePartDto) {
        SparePart sparePart= SparePartMapper.INSTANCE.toEntity(sparePartDto);
        try {
            SparePart sparePartSave = sparePartRepository.save(sparePart);
            return SparePartMapper.INSTANCE.toDto(sparePartSave);
        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось сохранить в базе!", e);
        }
    }

    @Override
    public SparePartDto updateSparePart(SparePartDto sparePartDto) {
        SparePart sparePart = this.sparePartRepository.findById(sparePartDto.getId())
                .orElseThrow(() -> new RuntimeException("апчасть не найдена"));

        SparePartMapper.INSTANCE.update(sparePartDto, sparePart);

        try{
            SparePart sparePartSave=sparePartRepository.save(sparePart);
            return  SparePartMapper.INSTANCE.toDto(sparePartSave);
        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось обновить в базе!", e);
        }
    }

    @Override
    public List<SparePartDto> findAllSparePart() {
        return SparePartMapper.INSTANCE.toResponseList(sparePartRepository.findAll());
    }


    @Override
    public void deleteSparePart(Long id) {
        SparePart sparePart=this.sparePartRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Статус оборудования не найден"));
        sparePartRepository.deleteById(id);
    }
}
