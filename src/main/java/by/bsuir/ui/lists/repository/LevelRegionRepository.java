package by.bsuir.ui.lists.repository;

import by.bsuir.ui.lists.entity.LevelRegion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelRegionRepository extends JpaRepository<LevelRegion, String> {

    List<LevelRegion> findByLevelRegionId(String levelRegionId);

}
