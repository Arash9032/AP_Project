package model.map.building.production;

import config.Constants;
import model.map.building.BuildingType;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;
import model.map.hex.HexResource;
import model.map.hex.TerrainType;

public class IronMine extends ProductionBuilding {

    public IronMine(Hex location) {
        super(
                location,
                Constants.IRON_MINE_WORKER_CAPACITY,
                Constants.IRON_MINE_BASE_PRODUCTION_RATE,
                InventoryResource.IRON,
                Constants.IRON_MINE_MAXIMUM_HP,
                BuildingType.IRON_MINE
        );

        if (location.getTerrain() != TerrainType.MOUNTAIN || location.getResource() != HexResource.IRON) {
            throw new IllegalArgumentException("Iron Mine can only be built on a MOUNTAIN hex with IRON resource.");
        }
    }

    @Override
    public int getMaximumHp() {
        return Constants.IRON_MINE_MAXIMUM_HP;
    }
}