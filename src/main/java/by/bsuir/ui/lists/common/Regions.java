package by.bsuir.ui.lists.common;

public class Regions {

    private String id;

    private String regionName;

    private String name;

    public Regions(String id, String regionName, String name) {
        this.id = id;
        this.regionName = regionName;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
