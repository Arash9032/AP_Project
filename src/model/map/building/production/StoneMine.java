package model.map.building.production;

import config.Constants;
import model.map.building.BuildingType;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;
import model.map.hex.HexResource;
import model.map.hex.TerrainType;

public class StoneMine extends ProductionBuilding {

    public StoneMine(Hex location) {
        super(
                location,
                Constants.STONE_MINE_WORKER_CAPACITY,
                Constants.STONE_MINE_BASE_PRODUCTION_RATE,
                InventoryResource.STONE,
                Constants.STONE_MINE_MAXIMUM_HP,
                BuildingType.STONE_MINE
        );

        if (location.getTerrain() != TerrainType.MOUNTAIN || location.getResource() != HexResource.STONE) {
            throw new IllegalArgumentException("Stone Mine can only be built on a MOUNTAIN hex with STONE resource.");
        }
    }

    @Override
    public int getMaximumHp() {
        return Constants.STONE_MINE_MAXIMUM_HP;
    }
}