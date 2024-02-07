package com.anderson.starwars.adapter.repositories;

import com.anderson.starwars.adapter.entity.PlanetEntityAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringPlanetRepository extends JpaRepository<PlanetEntityAdapter, UUID> {

    @Query("SELECT p FROM PlanetEntityAdapter p WHERE LOWER(p.name) = LOWER(:name)")
    Optional<PlanetEntityAdapter> findByName(@Param("name") String name);
}
