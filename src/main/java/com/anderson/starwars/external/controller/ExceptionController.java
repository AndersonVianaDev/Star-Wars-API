package com.anderson.starwars.external.controller;

import com.anderson.starwars.core.world.exceptions.InvalidDataException;
import com.anderson.starwars.core.world.exceptions.NotFoundException;
import com.anderson.starwars.core.world.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<StandardError> invalid(InvalidDataException e, HttpServletRequest request) {
        String error = "invalid data";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(NotFoundException e, HttpServletRequest request) {
        String error = "not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

}
