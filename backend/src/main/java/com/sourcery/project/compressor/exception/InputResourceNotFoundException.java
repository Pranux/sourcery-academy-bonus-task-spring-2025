package com.sourcery.project.compressor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InputResourceNotFoundException extends RuntimeException {
    public InputResourceNotFoundException(String message) {
        super(message);
    }
}
