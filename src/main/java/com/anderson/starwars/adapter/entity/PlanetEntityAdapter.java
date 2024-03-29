package com.anderson.starwars.adapter.entity;


import com.anderson.starwars.core.world.model.Planet;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_planets")
public class PlanetEntityAdapter {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String climate;
    private String ground;
    private Integer quantity;

    public PlanetEntityAdapter() {
    }

    public PlanetEntityAdapter(Planet planet) {
        this.name = planet.getName();
        this.climate = planet.getClimate();
        this.ground = planet.getGround();
        this.quantity = planet.getQuantity();
    }

    public Planet toPlanet() {
        return new Planet(this.id, this.name, this.climate, this.ground, this.quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanetEntityAdapter that = (PlanetEntityAdapter) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
