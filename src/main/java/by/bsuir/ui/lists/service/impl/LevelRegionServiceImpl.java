package by.bsuir.ui.lists.service.impl;

import by.bsuir.ui.lists.entity.LevelRegion;
import by.bsuir.ui.lists.repository.LevelRegionRepository;
import by.bsuir.ui.lists.service.LevelRegionService;

import java.util.List;
import java.util.Optional;

public class LevelRegionServiceImpl implements LevelRegionService {

    private final LevelRegionRepository levelRegionRepository;

    public LevelRegionServiceImpl(LevelRegionRepository levelRegionRepository) {
        this.levelRegionRepository = levelRegionRepository;
    }

    @Override
    public List<LevelRegion> findByLevelRegionId(String levelRegionId) {
        return levelRegionRepository.findByLevelRegionId(levelRegionId);
    }

    @Override
    public LevelRegion findById(String id) {
        Optional<LevelRegion> levelRegionOptional = levelRegionRepository.findById(id);
        return levelRegionOptional.orElse(null);
    }

    @Override
    public LevelRegion save(LevelRegion levelRegion) {
        return levelRegionRepository.save(levelRegion);
    }

    @Override
    public void remove(String levelRegionId) {
        levelRegionRepository.deleteById(levelRegionId);
    }

}
