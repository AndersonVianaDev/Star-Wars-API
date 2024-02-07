package com.anderson.starwars.core.world.service;

import com.anderson.starwars.core.world.dtos.PlanetDTO;
import com.anderson.starwars.core.world.model.Planet;

import java.util.UUID;

public interface PlanetService {

    void create(PlanetDTO dto);

    Planet findById(UUID id);
}
