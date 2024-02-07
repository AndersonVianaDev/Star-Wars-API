package com.anderson.starwars.adapter.repositories;

import com.anderson.starwars.adapter.entity.PlanetEntityAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringPlanetRepository extends JpaRepository<PlanetEntityAdapter, UUID> {
}
