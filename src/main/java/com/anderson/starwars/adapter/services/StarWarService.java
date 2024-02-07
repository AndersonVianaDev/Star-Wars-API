package com.anderson.starwars.adapter.services;

import com.anderson.starwars.core.world.dtos.PlanetClientDTO;
import com.anderson.starwars.core.world.service.PlanetsClientService;
import com.anderson.starwars.external.api.StarWarsApi;
import org.springframework.stereotype.Component;

@Component
public class StarWarService implements PlanetsClientService {

    private final StarWarsApi service;

    public StarWarService(StarWarsApi service) {
        this.service = service;
    }

    @Override
    public PlanetClientDTO getPlanet(String name) {
        PlanetClientDTO planetsClient = null;

        for (int i=1; i<=60; i++) {
            planetsClient = this.service.getPlanet(i);
            if(planetsClient.name().equalsIgnoreCase(name)) break;
        }

        return planetsClient;
    }
}
