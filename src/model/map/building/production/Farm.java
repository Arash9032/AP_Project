package model.map.building.production;

import config.Constants;
import model.map.building.BuildingType;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;
import model.map.hex.HexResource;
import model.map.hex.TerrainType;

public class Farm extends ProductionBuilding {

    public Farm(Hex location) {
        super(
                location,
                Constants.FARM_WORKER_CAPACITY,
                Constants.FARM_BASE_PRODUCTION_RATE,
                InventoryResource.FOOD,
                Constants.FARM_MAXIMUM_HP,
                BuildingType.FARM
        );

        if (location.getTerrain() != TerrainType.MEADOW || location.getResource() != HexResource.CROPS) {
            throw new IllegalArgumentException("Farm requires a MEADOW hex containing CROPS (Wheat/Rice).");
        }
    }

    @Override
    public int getMaximumHP() {
        return Constants.FARM_MAXIMUM_HP;
    }
}
