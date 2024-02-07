package com.anderson.starwars.external.controller;

import com.anderson.starwars.core.world.dtos.PlanetDTO;
import com.anderson.starwars.core.world.dtos.PlanetResponseDTO;
import com.anderson.starwars.core.world.model.Planet;
import com.anderson.starwars.core.world.service.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("api/v1/world")
public class PlanetController {

    private final PlanetService service;

    public PlanetController(PlanetService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody PlanetDTO dto) {
        this.service.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<PlanetResponseDTO> findById(@PathVariable UUID id) {
        Planet planet = this.service.findById(id);

        PlanetResponseDTO planetDTO = planet.toPlanetResponseDTO();

        return ResponseEntity.status(HttpStatus.OK).body(planetDTO);
    }
}
