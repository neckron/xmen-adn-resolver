package com.xmen.dna.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.xmen.dna.dto.response.DNAResponseDTO;

/**
 * This class listen for specific Exception clases in order to
 * handle the same response when they occur.
 * @author fr.rodriguez
 */
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DNAResponseDTO> handleArgumentException(MethodArgumentNotValidException ex){
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DNAResponseDTO.builder().isMutant(false).message(ex.getFieldError().getDefaultMessage()).build());
    }

}
