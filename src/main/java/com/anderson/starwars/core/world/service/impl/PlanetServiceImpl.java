package com.anderson.starwars.core.world.service.impl;

import com.anderson.starwars.core.world.dtos.PlanetDTO;
import com.anderson.starwars.core.world.model.Planet;
import com.anderson.starwars.core.world.repository.PlanetRepository;
import com.anderson.starwars.core.world.service.PlanetService;

public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository repository;

    public PlanetServiceImpl(PlanetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(PlanetDTO dto) {
        Planet planet = new Planet(dto);

        this.repository.save(planet);
    }
}
