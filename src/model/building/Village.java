package model.building;

import java.util.HashMap;
import java.util.Map;

import config.Constants;
import model.hex.Hex;
import model.hex.ResourceType;

public class Village extends Building {

    private static Map<ResourceType, Integer> createConstructionCost() {
        Map<ResourceType, Integer> cost = new HashMap<>();
        cost.put(ResourceType.WOOD, Constants.VILLAGE_WOOD_COST);
        cost.put(ResourceType.STONE, Constants.VILLAGE_STONE_COST);
        cost.put(ResourceType.IRON, Constants.VILLAGE_IRON_COST);
        return cost;
    }

    private static Map<ResourceType, Integer> createUpkeepCost() {
        Map<ResourceType, Integer> upkeep = new HashMap<>();
        upkeep.put(ResourceType.WOOD, Constants.VILLAGE_WOOD_UPKEEP);
        upkeep.put(ResourceType.STONE, Constants.VILLAGE_STONE_UPKEEP);
        upkeep.put(ResourceType.IRON, Constants.VILLAGE_IRON_UPKEEP);
        return upkeep;
    }

    public Village(Hex location) {
        super(
                location,
                Constants.VILLAGE_AP_COST,
                createConstructionCost(),
                createUpkeepCost()
        );
    }
}
