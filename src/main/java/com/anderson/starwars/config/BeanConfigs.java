package com.anderson.starwars.config;

import com.anderson.starwars.adapter.repositories.PlanetEntityRepository;
import com.anderson.starwars.adapter.services.StarWarService;
import com.anderson.starwars.core.world.service.PlanetService;
import com.anderson.starwars.core.world.service.impl.PlanetServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigs {

    @Bean
    public PlanetService worldService(PlanetEntityRepository repository, StarWarService starWarService) {
        return new PlanetServiceImpl(repository, starWarService);
    }
}
