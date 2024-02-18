package com.anderson.starwars.core.world.exceptions;

public class ExistingPlanetException extends RuntimeException {
    public ExistingPlanetException(String message) {
        super(message);
    }
}
