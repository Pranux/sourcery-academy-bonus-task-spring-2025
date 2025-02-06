package com.sourcery.project.compressor.controller;

import com.sourcery.project.compressor.dto.InputDto;
import com.sourcery.project.compressor.service.InputService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    
    @GetMapping("{id}")
    public ResponseEntity<InputDto>getInputByID(@PathVariable("id") int id) {
        InputDto inputDto = inputService.getInputById(id);
        return new ResponseEntity<>(inputDto, HttpStatus.OK);
    }
}
