package com.sourcery.project.compressor.service;

import com.sourcery.project.compressor.dto.InputDto;

public interface InputServiceInterface {

    InputDto createNewInput(InputDto inputDto);
    InputDto getInputById(int id);
}
