package com.anderson.starwars.external.controller;

import com.anderson.starwars.builders.PlanetBuilder;
import com.anderson.starwars.core.world.dtos.PlanetDTO;
import com.anderson.starwars.core.world.dtos.PlanetResponseDTO;
import com.anderson.starwars.core.world.model.Planet;
import com.anderson.starwars.core.world.service.PlanetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PlanetControllerTest {

    @InjectMocks
    private PlanetController controller;

    @Mock
    private PlanetService service;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .alwaysDo(print())
                .build();
    }

    @Test
    void postCreateSuccessfully() throws Exception {
        PlanetDTO dto = PlanetBuilder.newPlanetDTO();

        doNothing().when(service).create(dto);

        mockMvc.perform(post("/api/v1/world/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void getPlanetByIdSuccessfully() throws Exception {
        Planet planet = PlanetBuilder.newPlanet();

        when(service.findById(planet.getId())).thenReturn(planet);

        mockMvc.perform(get("/api/v1/world/find/id/{id}", planet.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(planet.toPlanetResponseDTO())));
    }

    @Test
    void getPlanetByNameSuccessfully() throws Exception {
        Planet planet = PlanetBuilder.newPlanet();

        when(service.findByName(planet.getName())).thenReturn(planet);

        mockMvc.perform(get("/api/v1/world/find/name/")
                .contentType(MediaType.APPLICATION_JSON)
                        .param("name", planet.getName()))
                .andExpect(status().isOk()).andExpect(content().json(asJsonString(planet.toPlanetResponseDTO())));
    }

    @Test
    void deletePlanetByIdSuccessfully() throws Exception {
        Planet planet = PlanetBuilder.newPlanet();

        doNothing().when(service).delete(planet.getId());

        mockMvc.perform(delete("/api/v1/world/delete/id/{id}", planet.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void getAllPlanetSuccessfully() throws Exception {
        List<Planet> planetList = List.of(PlanetBuilder.newPlanet(), PlanetBuilder.newPlanet());

        when(service.findAll()).thenReturn(planetList);

        List<PlanetResponseDTO> planetResponseDTOList = planetList.stream().map(p -> p.toPlanetResponseDTO()).toList();

        mockMvc.perform(get("/api/v1/world/findAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(planetResponseDTOList)));
    }



    private String asJsonString(final Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }

}