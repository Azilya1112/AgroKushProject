package com.example.agrokushproject.service.impl;

import com.example.agrokushproject.dto.CounterDto;
import com.example.agrokushproject.entity.Counter;
import com.example.agrokushproject.exceptions.RecordNotFoundException;
import com.example.agrokushproject.mapper.CounterMapper;
import com.example.agrokushproject.repositories.CounterRepository;
import com.example.agrokushproject.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CounterServiceImpl implements CounterService {

    private final CounterRepository counterRepository;

    @Override
    public CounterDto saveCounter(CounterDto counterDto) {
        Counter counter= CounterMapper.INSTANCE.toEntity(counterDto);
        try{
            Counter saveCounter=counterRepository.save(counter);
            return CounterMapper.INSTANCE.toDto(saveCounter);
        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось сохранить счетчик в базе!", e);
        }
    }

    @Override
    public CounterDto updateCounter(CounterDto counterDto) {
        Counter counter = this.counterRepository.findById(counterDto.getId())
                .orElseThrow(() -> new RuntimeException("Счетчик не найден"));

        CounterMapper.INSTANCE.update(counter, counterDto);

        try{
            Counter counterSave=counterRepository.save(counter);
            return  CounterMapper.INSTANCE.toDto(counterSave);
        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось обновить счетчик в базе!", e);
        }
    }

    @Override
    public List<CounterDto> findAllCounter() {
        return CounterMapper.INSTANCE.toResponseList(counterRepository.findAll());
    }

    @Override
    public void deleteCounter(Long id) {
        Counter counter = this.counterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Счетчик не найден"));

        counterRepository.deleteById(counter.getId());
    }
}
