package com.anderson.starwars.core.world.repository;

import com.anderson.starwars.core.world.model.Planet;

import java.util.List;
import java.util.UUID;

public interface PlanetRepository {

    void save(Planet planet);

    Planet findById(UUID id);

    Planet findByName(String name);

    void delete(Planet planet);

    List<Planet> findAll();
}
