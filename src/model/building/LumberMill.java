package model.building;

import java.util.HashMap;
import java.util.Map;

import config.Constants;
import model.hex.Hex;
import model.hex.ResourceType;
import model.hex.TerrainType;

public class LumberMill extends ProductionBuilding {

    private static Map<ResourceType, Integer> createConstructionCost() {
        Map<ResourceType, Integer> cost = new HashMap<>();
        cost.put(ResourceType.WOOD, Constants.LUMBER_MILL_WOOD_COST);
        return cost;
    }

    private static Map<ResourceType, Integer> createUpkeepCost() {
        Map<ResourceType, Integer> upkeep = new HashMap<>();
        upkeep.put(ResourceType.WOOD, Constants.LUMBER_MILL_WOOD_UPKEEP);
        return upkeep;
    }

    public LumberMill(Hex location) {
        super(
                location,
                Constants.LUMBER_MILL_AP_COST,
                createConstructionCost(),
                createUpkeepCost(),
                Constants.LUMBER_MILL_CAPACITY,
                Constants.LUMBER_MILL_RATE,
                ResourceType.WOOD
        );

        if (location.getTerrain() != TerrainType.FOREST) {
            throw new IllegalArgumentException("Lumber Mill can only be built on a FOREST hex.");
        }
    }
}