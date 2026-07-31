package model.map.building.production;

import config.Constants;
import model.map.building.BuildingType;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;
import model.map.hex.HexResource;
import model.map.hex.TerrainType;

public class Stable extends ProductionBuilding {

    public Stable(Hex location) {
        super(
                location,
                Constants.STABLE_WORKER_CAPACITY,
                Constants.STABLE_BASE_PRODUCTION_RATE,
                InventoryResource.FOOD,
                Constants.STABLE_MAXIMUM_HP,
                BuildingType.STABLE
        );

        if (location.getTerrain() != TerrainType.PLAIN || location.getResource() != HexResource.LIVESTOCK) {
            throw new IllegalArgumentException("Stable requires a PLAIN hex containing LIVESTOCK.");
        }
    }

    @Override
    public int getMaximumHp() {
        return Constants.STABLE_MAXIMUM_HP;
    }
}
