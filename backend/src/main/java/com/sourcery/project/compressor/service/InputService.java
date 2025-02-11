package com.sourcery.project.compressor.service;

import com.sourcery.project.compressor.actions.ChangedInput;
import com.sourcery.project.compressor.dto.InputDto;
import com.sourcery.project.compressor.entity.Input;
import com.sourcery.project.compressor.exception.InputResourceNotFoundException;
import com.sourcery.project.compressor.exception.InvalidInputException;
import com.sourcery.project.compressor.mapper.InputMapper;
import com.sourcery.project.compressor.repository.InputRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InputService implements InputServiceInterface {
    
    private InputRepository inputRepository;

    @Override
    public InputDto createNewInput(InputDto inputDto) {
        Input newInput = InputMapper.mapToInput(inputDto);
        
        if(!newInput.getText().isEmpty()) {
            if (newInput.getType().equals("compress")) {
                if (!newInput.getText().matches("[a-z]+")) {
                    throw new InvalidInputException("Input must be only lower case letters");
                }
            } else if (newInput.getType().equals("decompress")) {
                char[] c = newInput.getText().toCharArray();
                int i = 0;

                while (i < c.length) {
                    if (c[i] >= 'a' && c[i] <= 'z') {
                        i++;
                        if (i >= c.length || !(c[i] >= '0' && c[i] <= '9')) {
                            throw new InvalidInputException("Input must be lower case letters followed by a number");
                        }
                        while (i < c.length && c[i] >= '0' && c[i] <= '9') {
                            i++;
                        }
                    } else {
                        throw new InvalidInputException("Input must be lower case letters followed by a number");
                    }
                }
            }
        }
        
        Input savedInput = inputRepository.save(newInput);
        return InputMapper.mapToInputDto(savedInput);
    }
    
    @Override
    public List<InputDto> getAllInputs() {
        List<Input> allInputs = inputRepository.findAll();
        return allInputs.stream().map(InputMapper::mapToInputDto).collect(Collectors.toList());
    }
    
    @Override
    public InputDto getInputById(int id) {
        Input input = inputRepository.findById(id).orElseThrow(
                () -> new InputResourceNotFoundException("No input with such id"));
        return InputMapper.mapToInputDto(input);
    }
    
    @Override
    public InputDto getCompressedString(int id) {
        InputDto originalInput = getInputById(id);
        Input savedInput = InputMapper.mapToInput(originalInput);
        String answer = ChangedInput.compressed(savedInput.getText());
        savedInput.setText(answer);
        return InputMapper.mapToInputDto(savedInput);
    }
    
    @Override
    public InputDto getDecompressedString(int id) {
        InputDto originalInput = getInputById(id);
        Input savedInput = InputMapper.mapToInput(originalInput);
        String answer = ChangedInput.decompressed(savedInput.getText());
        savedInput.setText(answer);
        return InputMapper.mapToInputDto(savedInput);
    }
}
