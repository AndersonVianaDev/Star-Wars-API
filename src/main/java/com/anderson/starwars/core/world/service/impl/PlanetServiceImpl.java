package com.anderson.starwars.core.world.service.impl;

import com.anderson.starwars.core.world.dtos.PlanetDTO;
import com.anderson.starwars.core.world.dtos.PlanetClientDTO;
import com.anderson.starwars.core.world.model.Planet;
import com.anderson.starwars.core.world.repository.PlanetRepository;
import com.anderson.starwars.core.world.service.PlanetService;
import com.anderson.starwars.core.world.service.PlanetsClientService;

public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository repository;
    private final PlanetsClientService service;

    public PlanetServiceImpl(PlanetRepository repository, PlanetsClientService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void create(PlanetDTO dto) {
        Planet planet = new Planet(dto);

        PlanetClientDTO planetClient = this.service.getPlanet(planet.getName());

        if (planetClient == null) {
            planet.setQuantity(0);
        } else {
            planet.setQuantity(planetClient.films().size());
        }

        this.repository.save(planet);
    }
}
