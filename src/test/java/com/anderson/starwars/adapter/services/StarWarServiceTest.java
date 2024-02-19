package com.anderson.starwars.adapter.services;

import com.anderson.starwars.core.world.dtos.PlanetClientDTO;
import com.anderson.starwars.external.api.StarWarsApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StarWarServiceTest {

    @InjectMocks
    private StarWarService service;

    @Mock
    private StarWarsApi starWarsApi;

    @Test
    void getPlanet() {
        when(starWarsApi.getPlanet(1)).thenReturn(new PlanetClientDTO("Tatooine", List.of("https://swapi.dev/api/films/1/",
                "https://swapi.dev/api/films/3/",
                "https://swapi.dev/api/films/4/",
                "https://swapi.dev/api/films/5/",
                "https://swapi.dev/api/films/6/")));

        PlanetClientDTO planet = service.getPlanet("Tatooine");

        assertNotNull(planet);
        assertEquals("Tatooine", planet.name());
        assertEquals(5, planet.films().size());

        verify(starWarsApi, times(1)).getPlanet(1);
    }

    @Test
    void getPlanetReturnNull() {
        when(starWarsApi.getPlanet(anyInt())).thenReturn(new PlanetClientDTO("Planeta", List.of()));

        PlanetClientDTO planet = service.getPlanet("Mundo");

        assertNull(planet);

        verify(starWarsApi, times(60)).getPlanet(anyInt());

    }
}