package com.anderson.starwars.core.world.model;

import com.anderson.starwars.core.world.dtos.PlanetDTO;
import com.anderson.starwars.core.world.dtos.PlanetResponseDTO;

import java.util.Objects;
import java.util.UUID;

public class Planet {

    private UUID id;
    private String name;
    private String climate;
    private String ground;
    private Integer quantity;

    public Planet(UUID id, String name, String climate, String ground, Integer quantity) {
        this.id = id;
        this.name = name;
        this.climate = climate;
        this.ground = ground;
        this.quantity = quantity;
    }

    public Planet(PlanetDTO dto) {
        this.name = dto.name();
        this.climate = dto.climate();
        this.ground = dto.ground();
    }

    public PlanetResponseDTO toPlanetResponseDTO() {
        return new PlanetResponseDTO(this.name, this.climate, this.ground, this.quantity);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(id, planet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
