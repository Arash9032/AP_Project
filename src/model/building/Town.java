package model.building;

import java.util.HashMap;
import java.util.Map;

import config.Constants;
import model.hex.Hex;
import model.hex.ResourceType;

public class Town extends Building {

    private static Map<ResourceType, Integer> createConstructionCost() {
        Map<ResourceType, Integer> cost = new HashMap<>();
        cost.put(ResourceType.WOOD, Constants.TOWN_WOOD_COST);
        cost.put(ResourceType.STONE, Constants.TOWN_STONE_COST);
        cost.put(ResourceType.IRON, Constants.TOWN_IRON_COST);
        return cost;
    }

    private static Map<ResourceType, Integer> createUpkeepCost() {
        Map<ResourceType, Integer> upkeep = new HashMap<>();
        upkeep.put(ResourceType.WOOD, Constants.TOWN_WOOD_UPKEEP);
        upkeep.put(ResourceType.STONE, Constants.TOWN_STONE_UPKEEP);
        upkeep.put(ResourceType.IRON, Constants.TOWN_IRON_UPKEEP);
        return upkeep;
    }

    public Town(Hex location) {
        super(
                location,
                Constants.TOWN_AP_COST,
                createConstructionCost(),
                createUpkeepCost()
        );
    }
}
