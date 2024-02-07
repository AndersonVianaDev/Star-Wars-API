package com.anderson.starwars.core.world.dtos;

import java.util.List;

public record PlanetClientDTO(String name, List<String> films) {
}
