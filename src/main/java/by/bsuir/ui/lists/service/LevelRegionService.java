package by.bsuir.ui.lists.service;

import by.bsuir.ui.lists.entity.LevelRegion;

import java.util.List;

public interface LevelRegionService {

    List<LevelRegion> findByLevelRegionId(String levelRegionId);

    LevelRegion findById(String id);

    LevelRegion save(LevelRegion levelRegion);

    void remove(String levelRegionId);

}
