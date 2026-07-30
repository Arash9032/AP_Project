package model.map.building.production;

import java.util.HashMap;
import java.util.Map;

import config.Constants;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;
import model.map.hex.HexResource;
import model.map.hex.TerrainType;

public class IronMine extends ProductionBuilding {

    private static Map<InventoryResource, Integer> createConstructionCost() {
        Map<InventoryResource, Integer> cost = new HashMap<>();
        cost.put(InventoryResource.WOOD, Constants.IRON_MINE_WOOD_COST);
        return cost;
    }

    private static Map<InventoryResource, Integer> createUpkeepCost() {
        Map<InventoryResource, Integer> upkeep = new HashMap<>();
        upkeep.put(InventoryResource.WOOD, Constants.IRON_MINE_WOOD_UPKEEP);
        return upkeep;
    }

    public IronMine(Hex location) {
        super(
                location,
                Constants.IRON_MINE_AP_COST,
                createConstructionCost(),
                createUpkeepCost(),
                Constants.IRON_MINE_CAPACITY,
                Constants.IRON_MINE_RATE,
                InventoryResource.IRON,
                Constants.IRON_MINE_MAXIMUM_HP
        );

        if (location.getTerrain() != TerrainType.MOUNTAIN || location.getResource() != HexResource.IRON) {
            throw new IllegalArgumentException("Iron Mine can only be built on a MOUNTAIN hex with IRON resource.");
        }
    }

    @Override
    public int getMaximumHP() {
        return Constants.IRON_MINE_MAXIMUM_HP;
    }
}