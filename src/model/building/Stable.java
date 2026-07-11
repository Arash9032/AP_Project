package model.building;

import java.util.HashMap;
import java.util.Map;
import config.Constants;
import model.hex.Hex;
import model.hex.ResourceType;
import model.hex.TerrainType;

public class Stable extends Building {

    private static Map<ResourceType, Integer> createConstructionCost() {
        Map<ResourceType, Integer> cost = new HashMap<>();
        cost.put(ResourceType.WOOD, Constants.STABLE_WOOD_COST);
        return cost;
    }

    private static Map<ResourceType, Integer> createUpkeepCost() {
        Map<ResourceType, Integer> upkeep = new HashMap<>();
        upkeep.put(ResourceType.WOOD, Constants.STABLE_WOOD_UPKEEP);
        return upkeep;
    }

    public Stable(Hex location) {
        super(
                location,
                Constants.STABLE_AP_COST,
                createConstructionCost(),
                createUpkeepCost()
        );

        if (location.getTerrain() != TerrainType.PLAIN || location.getResource() != ResourceType.FOOD) {
            throw new IllegalArgumentException("Stable requires a PLAIN hex containing FOOD (Cows/Sheep).");
        }
    }
}
