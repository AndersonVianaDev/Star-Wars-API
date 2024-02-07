package com.anderson.starwars.core.world.service;

import com.anderson.starwars.core.world.dtos.PlanetClientDTO;

public interface PlanetsClientService {
    PlanetClientDTO getPlanet(String name);
}

