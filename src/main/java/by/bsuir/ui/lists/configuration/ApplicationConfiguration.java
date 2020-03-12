package by.bsuir.ui.lists.configuration;

import by.bsuir.ui.lists.repository.LevelRegionRepository;
import by.bsuir.ui.lists.service.LevelRegionService;
import by.bsuir.ui.lists.service.impl.LevelRegionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private LevelRegionRepository levelRegionRepository;

    @Bean
    public LevelRegionService levelRegionService() {
        return new LevelRegionServiceImpl(levelRegionRepository);
    }

}
