package com.sourcery.project.compressor.service;

import com.sourcery.project.compressor.dto.InputDto;
import com.sourcery.project.compressor.entity.Input;
import com.sourcery.project.compressor.exception.InputResourseNotFoundException;
import com.sourcery.project.compressor.mapper.InputMapper;
import com.sourcery.project.compressor.repository.InputRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InputService implements InputServiceInterface {
    
    private InputRepository inputRepository;

    @Override
    public InputDto createNewInput(InputDto inputDto) {
        Input newInput = InputMapper.mapToInput(inputDto);
        Input savedInput = inputRepository.save(newInput);
        return InputMapper.mapToInputDto(savedInput);
    }
    
    @Override
    public InputDto getInputById(int id) {
        Input input = inputRepository.findById(id).orElseThrow(
                () -> new InputResourseNotFoundException("No input with such id"));
        return InputMapper.mapToInputDto(input);
    }
}
