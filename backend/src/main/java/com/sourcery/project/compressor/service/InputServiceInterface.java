package com.sourcery.project.compressor.service;

import com.sourcery.project.compressor.dto.InputDto;

import java.util.List;

public interface InputServiceInterface {

    InputDto createNewInput(InputDto inputDto);
    List<InputDto> getAllInputs();
    InputDto getInputById(int id);
    InputDto getCompressedString(int id);
    InputDto getDecompressedString(int id);
}
