package com.anderson.starwars.external.controller;

import com.anderson.starwars.core.world.dtos.PlanetDTO;
import com.anderson.starwars.core.world.dtos.PlanetResponseDTO;
import com.anderson.starwars.core.world.model.Planet;
import com.anderson.starwars.core.world.service.PlanetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("api/v1/world")
@Tag(name = "star-wars-api")
public class PlanetController {

    private final PlanetService service;

    public PlanetController(PlanetService service) {
        this.service = service;
    }

    @PostMapping("/create")
    @Operation(summary = "create planet", method = "POST")
    public ResponseEntity<Void> create(@RequestBody PlanetDTO dto) {
        this.service.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/find/id/{id}")
    @Operation(summary = "find planet by id", method = "GET")
    public ResponseEntity<PlanetResponseDTO> findById(@PathVariable UUID id) {
        Planet planet = this.service.findById(id);

        PlanetResponseDTO planetDTO = planet.toPlanetResponseDTO();

        return ResponseEntity.status(HttpStatus.OK).body(planetDTO);
    }

    @GetMapping("find/name/")
    @Operation(summary = "find planet by name", method = "GET")
    public ResponseEntity<PlanetResponseDTO> findByName(@RequestParam(name = "name") String name) {
        Planet planet = this.service.findByName(name);

        PlanetResponseDTO planetDTO = planet.toPlanetResponseDTO();

        return ResponseEntity.status(HttpStatus.OK).body(planetDTO);
    }

    @DeleteMapping("delete/id/{id}")
    @Operation(summary = "delete planet", method = "DELETE")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.service.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("findAll")
    @Operation(summary = "find all planet", method = "GET")
    public ResponseEntity<List<PlanetResponseDTO>> findAll() {
        List<Planet> list = this.service.findAll();

        List<PlanetResponseDTO> planetResponseDTOList = list.stream().map(p -> p.toPlanetResponseDTO()).toList();

        return ResponseEntity.status(HttpStatus.OK).body(planetResponseDTOList);
    }
}
