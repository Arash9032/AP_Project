package controller.eventbus;

import model.map.building.BuildingType;
import model.map.hex.Hex;

public class BuildingPlacedEvent implements GameEvent{
    private final BuildingType buildingType;
    private final Hex buildingHex;

    public BuildingPlacedEvent(BuildingType buildingType, Hex buildingHex) {
        this.buildingType = buildingType;
        this.buildingHex = buildingHex;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public Hex getBuildingHex() {
        return buildingHex;
    }
}
