package model.map.building.production;

import java.util.HashMap;
import java.util.Map;
import config.Constants;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;
import model.map.hex.HexResource;
import model.map.hex.TerrainType;

public class Farm extends ProductionBuilding {

    private static Map<InventoryResource, Integer> createConstructionCost() {
        Map<InventoryResource, Integer> cost = new HashMap<>();
        cost.put(InventoryResource.WOOD, Constants.FARM_WOOD_COST);
        return cost;
    }

    private static Map<InventoryResource, Integer> createUpkeepCost() {
        Map<InventoryResource, Integer> upkeep = new HashMap<>();
        upkeep.put(InventoryResource.WOOD, Constants.FARM_WOOD_UPKEEP);
        return upkeep;
    }

    public Farm(Hex location) {
        super(
                location,
                Constants.FARM_AP_COST,
                createConstructionCost(),
                createUpkeepCost(),
                Constants.FARM_CAPACITY,
                Constants.FARM_RATE,
                InventoryResource.FOOD,
                Constants.FARM_MAXIMUM_HP

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
