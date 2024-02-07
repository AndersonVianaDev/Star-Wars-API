package com.anderson.starwars.core.world.repository;

import com.anderson.starwars.core.world.model.Planet;

public interface PlanetRepository {

    void save(Planet planet);
}
