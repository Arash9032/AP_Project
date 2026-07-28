package model.map.building.production;

import java.util.HashMap;
import java.util.Map;

import config.Constants;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;
import model.map.hex.HexResource;
import model.map.hex.TerrainType;

public class StoneMine extends ProductionBuilding {

    private static Map<InventoryResource, Integer> createConstructionCost() {
        Map<InventoryResource, Integer> cost = new HashMap<>();
        cost.put(InventoryResource.WOOD, Constants.STONE_MINE_WOOD_COST);
        return cost;
    }

    private static Map<InventoryResource, Integer> createUpkeepCost() {
        Map<InventoryResource, Integer> upkeep = new HashMap<>();
        upkeep.put(InventoryResource.WOOD, Constants.STONE_MINE_WOOD_UPKEEP);
        return upkeep;
    }

    public StoneMine(Hex location) {
        super(
                location,
                Constants.STONE_MINE_AP_COST,
                createConstructionCost(),
                createUpkeepCost(),
                Constants.STONE_MINE_CAPACITY,
                Constants.STONE_MINE_RATE,
                InventoryResource.STONE,
                Constants.STONE_MINE_HP
        );

        if (location.getTerrain() != TerrainType.MOUNTAIN || location.getResource() != HexResource.STONE) {
            throw new IllegalArgumentException("Stone Mine can only be built on a MOUNTAIN hex with STONE resource.");
        }
    }
}