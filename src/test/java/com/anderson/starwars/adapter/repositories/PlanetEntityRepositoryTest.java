package com.anderson.starwars.adapter.repositories;

import com.anderson.starwars.adapter.entity.PlanetEntityAdapter;
import com.anderson.starwars.builders.PlanetBuilder;
import com.anderson.starwars.core.world.model.Planet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlanetEntityRepositoryTest {

    @InjectMocks
    private PlanetEntityRepository repository;

    @Mock
    private SpringPlanetRepository planetRepository;

    @Test
    void save() {
        Planet planet = new Planet(PlanetBuilder.newPlanetDTO());
        PlanetEntityAdapter planetEntityAdapter = new PlanetEntityAdapter(planet);

        repository.save(planet);

        verify(planetRepository, times(1)).save(planetEntityAdapter);
    }

    @Test
    void findById() {
        Planet planet = PlanetBuilder.newPlanet();
        PlanetEntityAdapter planetEntityAdapter = new PlanetEntityAdapter(planet);

        when(planetRepository.findById(planet.getId())).thenReturn(Optional.of(planetEntityAdapter));

        Planet planetFound = repository.findById(planet.getId());

        verify(planetRepository, times(1)).findById(planet.getId());

        assertNotNull(planetFound);
    }

    @Test
    void findByIdNull() {
        UUID id = UUID.randomUUID();

        when(planetRepository.findById(id)).thenReturn(Optional.empty());

        Planet planet = repository.findById(id);

        verify(planetRepository, times(1)).findById(id);

        assertNull(planet);
    }

    @Test
    void findByName() {
        Planet planet = PlanetBuilder.newPlanet();
        PlanetEntityAdapter planetEntityAdapter = new PlanetEntityAdapter(planet);

        when(planetRepository.findByName(planet.getName())).thenReturn(Optional.of(planetEntityAdapter));

        Planet planetFound = repository.findByName(planet.getName());

        verify(planetRepository, times(1)).findByName(planet.getName());

        assertNotNull(planetFound);
    }

    @Test
    void findByNameNull() {
        String name = "null";

        when(planetRepository.findByName(name)).thenReturn(Optional.empty());

        Planet planetNull = repository.findByName(name);

        verify(planetRepository, times(1)).findByName(name);

        assertNull(planetNull);
    }

    @Test
    void delete() {
        Planet planet = PlanetBuilder.newPlanet();
        PlanetEntityAdapter planetEntityAdapter = new PlanetEntityAdapter(planet);

        when(planetRepository.findById(planet.getId())).thenReturn(Optional.of(planetEntityAdapter));

        repository.delete(planet);

        verify(planetRepository, times(1)).delete(planetEntityAdapter);
    }

    @Test
    void findAll() {
        List<PlanetEntityAdapter> list = List.of(new PlanetEntityAdapter(PlanetBuilder.newPlanet()), new PlanetEntityAdapter(PlanetBuilder.newPlanet()));

        when(planetRepository.findAll()).thenReturn(list);

        List<Planet> listPlanet = repository.findAll();

        verify(planetRepository, times(1)).findAll();

        assertFalse(listPlanet.isEmpty());
    }
}