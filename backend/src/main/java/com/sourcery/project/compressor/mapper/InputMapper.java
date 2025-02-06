package com.sourcery.project.compressor.mapper;

import com.sourcery.project.compressor.dto.InputDto;
import com.sourcery.project.compressor.entity.Input;

public class InputMapper {
    public static InputDto mapToInputDto(Input input){
        return new InputDto(
                input.getId(),
                input.getText()
        );
    }

    public static Input mapToInput(InputDto inputDto){
        return new Input(
                inputDto.getId(),
                inputDto.getText()
        );
    }
}
