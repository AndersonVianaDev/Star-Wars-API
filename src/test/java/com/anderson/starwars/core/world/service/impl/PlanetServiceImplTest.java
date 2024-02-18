package com.anderson.starwars.core.world.service.impl;

import com.anderson.starwars.builders.PlanetBuilder;
import com.anderson.starwars.core.world.dtos.PlanetClientDTO;
import com.anderson.starwars.core.world.dtos.PlanetDTO;
import com.anderson.starwars.core.world.exceptions.ExistingPlanetException;
import com.anderson.starwars.core.world.exceptions.InvalidDataException;
import com.anderson.starwars.core.world.exceptions.NotFoundException;
import com.anderson.starwars.core.world.model.Planet;
import com.anderson.starwars.core.world.repository.PlanetRepository;
import com.anderson.starwars.core.world.service.PlanetsClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlanetServiceImplTest {
    @InjectMocks
    private PlanetServiceImpl service;
    @Mock
    private PlanetRepository repository;
    @Mock
    private PlanetsClientService serviceExternal;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createPlanetSuccessfully() {
        PlanetDTO dto = PlanetBuilder.newPlanetDTO();
        Planet planet = new Planet(dto);

        when(this.repository.findByName("Mundo")).thenReturn(null);

        service.create(dto);

        verify(serviceExternal, times(1)).getPlanet(planet.getName());
        verify(repository, times(1)).save(planet);
    }

    @Test
    void createPlanetFoundInExternalAPI() {
        PlanetDTO dto = PlanetBuilder.newPlanetDTO();
        Planet planet = new Planet(dto);
        PlanetClientDTO planetClient = PlanetBuilder.newPlanetClientDTO();

        when(repository.findByName(dto.name())).thenReturn(null);

        when(serviceExternal.getPlanet(planet.getName())).thenReturn(planetClient);

        planet.setQuantity(planetClient.films().size());

        service.create(dto);

        verify(repository, times(1)).save(planet);
        verify(serviceExternal, times(1)).getPlanet(planet.getName());

        assertEquals(2, planet.getQuantity());
    }

    @Test
    void failedCreatePlanet() {
        PlanetDTO dto = PlanetBuilder.newPlanetDTO();
        Planet planet = PlanetBuilder.newPlanet();
        when(this.repository.findByName(dto.name())).thenReturn(planet);

        ExistingPlanetException exception = assertThrows(ExistingPlanetException.class, () -> service.create(dto));

        assertEquals("Existing planet", exception.getMessage());
    }

    @Test
    void failedNotFoundPlanetFindById() {
        UUID id = UUID.randomUUID();
        when(this.repository.findById(id)).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.findById(id));

        assertEquals("Planet with id "+ id +" not found !", exception.getMessage());
    }

    @Test
    void failedNullIdFindById() {
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> service.findById(null));

        assertEquals("Required id", exception.getMessage());
    }

    @Test
    void FindByIdSuccessfully() {
        Planet planet = PlanetBuilder.newPlanet();

        when(repository.findById(planet.getId())).thenReturn(planet);

        Planet planetFound = service.findById(planet.getId());

        verify(repository, times(1)).findById(planet.getId());

        assertEquals(planet, planetFound);
    }

    @Test
    void failedNotFoundPlanetFindByName() {
        String name = "name";
        when(this.repository.findByName(name)).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.findByName(name));

        assertEquals("Planet with name "+ name +" not found", exception.getMessage());
    }

    @Test
    void failedNullNameFindByName() {
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> service.findByName(null));

        assertEquals("Required name", exception.getMessage());
    }

    @Test
    void findByName() {
        Planet planet = PlanetBuilder.newPlanet();

        when(repository.findByName(planet.getName())).thenReturn(planet);

        Planet planetFound = service.findByName(planet.getName());

        verify(repository, times(1)).findByName(planet.getName());

        assertEquals(planet, planetFound);
    }

    @Test
    void failedDelete() {
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> service.delete(null));

        assertEquals("Required id", exception.getMessage());
    }

    @Test
    void deleteSuccessfully() {
        Planet planet = PlanetBuilder.newPlanet();

        when(repository.findById(planet.getId())).thenReturn(planet);

        when(service.findById(planet.getId())).thenReturn(planet);

        service.delete(planet.getId());

        verify(repository, times(1)).delete(planet);
    }


    @Test
    void findAll() {

        java.util.List<Planet> planetList = Arrays.asList(PlanetBuilder.newPlanet(), PlanetBuilder.newPlanet());

        when(repository.findAll()).thenReturn(planetList);

        service.findAll();

        verify(repository, times(1)).findAll();

    }
}