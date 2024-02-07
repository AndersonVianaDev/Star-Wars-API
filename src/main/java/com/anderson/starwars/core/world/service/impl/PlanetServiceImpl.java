package com.anderson.starwars.core.world.service.impl;

import com.anderson.starwars.core.world.dtos.PlanetDTO;
import com.anderson.starwars.core.world.dtos.PlanetClientDTO;
import com.anderson.starwars.core.world.exceptions.InvalidDataException;
import com.anderson.starwars.core.world.exceptions.NotFoundException;
import com.anderson.starwars.core.world.model.Planet;
import com.anderson.starwars.core.world.repository.PlanetRepository;
import com.anderson.starwars.core.world.service.PlanetService;
import com.anderson.starwars.core.world.service.PlanetsClientService;

import java.util.List;
import java.util.UUID;

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

    @Override
    public Planet findById(UUID id) {
        if(id == null) {
            throw new InvalidDataException("Required id");
        }

        Planet planet = this.repository.findById(id);

        if(planet == null) {
            throw new NotFoundException("Planet with id "+ id +" not found !");
        }

        return planet;
    }

    @Override
    public Planet findByName(String name) {
        if(name == null) {
            throw new InvalidDataException("Required name");
        }

        Planet planet = this.repository.findByName(name);

        if(planet == null) {
            throw new NotFoundException("Planet with name "+ name +" not found");
        }

        return planet;
    }

    @Override
    public void delete(UUID id) {
        if(id == null) {
            throw new InvalidDataException("Required id");
        }

        Planet planet = this.findById(id);

        this.repository.delete(planet);
    }

    @Override
    public List<Planet> findAll() {
        List<Planet> list = this.repository.findAll();

        return list;
    }

}
