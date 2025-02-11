package com.sourcery.project.compressor.controller;

import com.sourcery.project.compressor.dto.InputDto;
import com.sourcery.project.compressor.service.InputService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/input")
@AllArgsConstructor
public class InputController {
    
    private final InputService inputService;
    
    @PostMapping
    public ResponseEntity<InputDto> createNewInput(@RequestBody InputDto inputDto) {
        InputDto savedInput = inputService.createNewInput(inputDto);
        return new ResponseEntity<>(savedInput, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<InputDto>> getAllInputs() {
        List<InputDto> allInputs = inputService.getAllInputs();
        return new ResponseEntity<>(allInputs, HttpStatus.OK);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<InputDto> getInputByID(@PathVariable("id") int id) {
        InputDto inputDto = inputService.getInputById(id);
        return new ResponseEntity<>(inputDto, HttpStatus.OK);
    }
    
    @GetMapping("{id}/compressed")
    public ResponseEntity<InputDto> getCompressedString(@PathVariable("id") int id) {
        InputDto inputDto = inputService.getCompressedString(id);
        return new ResponseEntity<>(inputDto, HttpStatus.OK);
    }

    @GetMapping("{id}/decompressed")
    public ResponseEntity<InputDto> getDecompressedString(@PathVariable("id") int id) {
        InputDto inputDto = inputService.getDecompressedString(id);
        return new ResponseEntity<>(inputDto, HttpStatus.OK);
    }
}
