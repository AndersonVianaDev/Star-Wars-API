package com.anderson.starwars.builders;

import com.anderson.starwars.core.world.dtos.PlanetClientDTO;
import com.anderson.starwars.core.world.dtos.PlanetDTO;
import com.anderson.starwars.core.world.model.Planet;

import java.util.List;
import java.util.UUID;

public class PlanetBuilder {
    public static Planet newPlanet() {
        UUID id = UUID.randomUUID();
        return new Planet(id, "Mundo", "Frio", "Montanha", 0);
    }

    public static PlanetDTO newPlanetDTO() {
        return new PlanetDTO("Mundo", "Frio", "Montanha");
    }

    public static PlanetClientDTO newPlanetClientDTO() {
        return new PlanetClientDTO("Mundo", List.of("star-wars 1", "star-wars 2"));
    }
}
