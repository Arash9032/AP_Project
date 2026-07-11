package model.building;

import java.util.HashMap;
import java.util.Map;
import config.Constants;
import model.hex.Hex;
import model.hex.ResourceType;
import model.hex.TerrainType;

public class Farm extends ProductionBuilding {

    private static Map<ResourceType, Integer> createConstructionCost() {
        Map<ResourceType, Integer> cost = new HashMap<>();
        cost.put(ResourceType.WOOD, Constants.FARM_WOOD_COST);
        return cost;
    }

    private static Map<ResourceType, Integer> createUpkeepCost() {
        Map<ResourceType, Integer> upkeep = new HashMap<>();
        upkeep.put(ResourceType.WOOD, Constants.FARM_WOOD_UPKEEP);
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
                ResourceType.FOOD
        );

        if (location.getTerrain() != TerrainType.MEADOW || location.getResource() != ResourceType.FOOD) {
            throw new IllegalArgumentException("Farm requires a MEADOW hex containing FOOD (Wheat/Rice).");
        }
    }
}
