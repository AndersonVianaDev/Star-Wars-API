package com.anderson.starwars.adapter.repositories;

import com.anderson.starwars.adapter.entity.PlanetEntityAdapter;
import com.anderson.starwars.core.world.model.Planet;
import com.anderson.starwars.core.world.repository.PlanetRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PlanetEntityRepository implements PlanetRepository {

    private final SpringPlanetRepository repository;

    public PlanetEntityRepository(SpringPlanetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Planet planet) {
        PlanetEntityAdapter worldEntity = new PlanetEntityAdapter(planet);

        this.repository.save(worldEntity);
    }

    @Override
    public Planet findById(UUID id) {
        Optional<PlanetEntityAdapter> planetEntity = this.repository.findById(id);

        if(planetEntity.isEmpty()) {
            return null;
        }

        return planetEntity.get().toPlanet();
    }
}
