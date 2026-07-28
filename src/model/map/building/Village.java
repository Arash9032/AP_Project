package model.map.building;

import java.util.HashMap;
import java.util.Map;

import config.Constants;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;
import model.map.hex.HexResource;

public class Village extends Building {

    private static Map<InventoryResource, Integer> createConstructionCost() {
        Map<InventoryResource, Integer> cost = new HashMap<>();
        cost.put(InventoryResource.WOOD, Constants.VILLAGE_WOOD_COST);
        cost.put(InventoryResource.STONE, Constants.VILLAGE_STONE_COST);
        cost.put(InventoryResource.IRON, Constants.VILLAGE_IRON_COST);
        return cost;
    }

    private static Map<InventoryResource, Integer> createUpkeepCost() {
        Map<InventoryResource, Integer> upkeep = new HashMap<>();
        upkeep.put(InventoryResource.WOOD, Constants.VILLAGE_WOOD_UPKEEP);
        upkeep.put(InventoryResource.STONE, Constants.VILLAGE_STONE_UPKEEP);
        upkeep.put(InventoryResource.IRON, Constants.VILLAGE_IRON_UPKEEP);
        return upkeep;
    }

    public Village(Hex location) {
        super(
                location,
                Constants.VILLAGE_AP_COST,
                createConstructionCost(),
                createUpkeepCost(),
                Constants.VILLAGE_HP
        );
    }
}
