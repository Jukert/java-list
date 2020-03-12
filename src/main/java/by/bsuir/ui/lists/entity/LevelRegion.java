package by.bsuir.ui.lists.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "level_region")
public class LevelRegion {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "level_name")
    private String levelName;

    @Column(name = "name")
    private String name;

    @Column(name = "level_region_id")
    private String levelRegionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevelRegionId() {
        return levelRegionId;
    }

    public void setLevelRegionId(String levelRegionId) {
        this.levelRegionId = levelRegionId;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
