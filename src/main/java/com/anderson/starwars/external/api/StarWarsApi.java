package com.anderson.starwars.external.api;

import com.anderson.starwars.core.world.dtos.PlanetClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "star-wars", url = "https://swapi.dev/api")
public interface StarWarsApi {
    @GetMapping("/planets/{id}")
    PlanetClientDTO getPlanet(@PathVariable Integer id);

}
