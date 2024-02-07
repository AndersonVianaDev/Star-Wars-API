package com.anderson.starwars.core.world.model;

import com.anderson.starwars.core.world.dtos.PlanetDTO;

import java.util.Objects;
import java.util.UUID;

public class Planet {

    private UUID id;
    private String name;
    private String climate;
    private String ground;

    public Planet(UUID id, String name, String climate, String ground) {
        this.id = id;
        this.name = name;
        this.climate = climate;
        this.ground = ground;
    }

    public Planet(PlanetDTO dto) {
        this.name = dto.name();
        this.climate = dto.climate();
        this.ground = dto.ground();
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
