package model.map.building.production;

import config.Constants;
import model.map.building.BuildingType;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;
import model.map.hex.TerrainType;

public class LumberMill extends ProductionBuilding {

    public LumberMill(Hex location) {
        super(
                location,
                Constants.LUMBER_MILL_WORKER_CAPACITY,
                Constants.LUMBER_MILL_BASE_PRODUCTION_RATE,
                InventoryResource.WOOD,
                Constants.LUMBER_MILL_MAXIMUM_HP,
                BuildingType.LUMBER_MILL
        );

        if (location.getTerrain() != TerrainType.FOREST) {
            throw new IllegalArgumentException("Lumber Mill can only be built on a FOREST hex.");
        }
    }

    @Override
    public int getMaximumHp() {
        return Constants.LUMBER_MILL_MAXIMUM_HP;
    }
}