package com.anderson.starwars.external.controller;

import com.anderson.starwars.core.world.exceptions.ExistingPlanetException;
import com.anderson.starwars.core.world.exceptions.InvalidDataException;
import com.anderson.starwars.core.world.exceptions.NotFoundException;
import com.anderson.starwars.core.world.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExceptionControllerTest {

    @InjectMocks
    private ExceptionController exceptionController;

    @Mock
    private HttpServletRequest request;

    @Test
    void testInvalidData() {
        InvalidDataException exception = new InvalidDataException("Invalid data message");

        when(request.getRequestURI()).thenReturn("/api/v1/world/*");

        ResponseEntity<StandardError> response = exceptionController.invalid(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("invalid data", response.getBody().getError());
    }

    @Test
    void testNotFound() {
        NotFoundException exception = new NotFoundException("Not found message");

        when(request.getRequestURI()).thenReturn("/api/v1/world/*");

        ResponseEntity<StandardError> response = exceptionController.notFound(exception, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("not found", response.getBody().getError());
    }

    @Test
    void testExistingPlanet() {
        ExistingPlanetException exception = new ExistingPlanetException("existing planet");

        when(request.getRequestURI()).thenReturn("/ap1/v1/world/*");

        ResponseEntity<StandardError> response = exceptionController.existingPlanet(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("existing planet", response.getBody().getError());
    }


}
