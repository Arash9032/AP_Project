package model.map.building;

import java.util.HashMap;
import java.util.Map;

import config.Constants;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;

public class Town extends Building {

    private static Map<InventoryResource, Integer> createConstructionCost() {
        Map<InventoryResource, Integer> cost = new HashMap<>();
        cost.put(InventoryResource.WOOD, Constants.TOWN_WOOD_COST);
        cost.put(InventoryResource.STONE, Constants.TOWN_STONE_COST);
        cost.put(InventoryResource.IRON, Constants.TOWN_IRON_COST);
        return cost;
    }

    private static Map<InventoryResource, Integer> createUpkeepCost() {
        Map<InventoryResource, Integer> upkeep = new HashMap<>();
        upkeep.put(InventoryResource.WOOD, Constants.TOWN_WOOD_UPKEEP);
        upkeep.put(InventoryResource.STONE, Constants.TOWN_STONE_UPKEEP);
        upkeep.put(InventoryResource.IRON, Constants.TOWN_IRON_UPKEEP);
        return upkeep;
    }

    public Town(Hex location) {
        super(
                location,
                Constants.TOWN_AP_COST,
                createConstructionCost(),
                createUpkeepCost(),
                Constants.TOWN_MAXIMUM_HP
        );
    }

    @Override
    public int getMaximumHP() {
        return Constants.TOWN_MAXIMUM_HP;
    }
}
