package model.map.building.production;

import java.util.HashMap;
import java.util.Map;

import config.Constants;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;
import model.map.hex.HexResource;
import model.map.hex.TerrainType;

public class LumberMill extends ProductionBuilding {

    private static Map<InventoryResource, Integer> createConstructionCost() {
        Map<InventoryResource, Integer> cost = new HashMap<>();
        cost.put(InventoryResource.WOOD, Constants.LUMBER_MILL_WOOD_COST);
        return cost;
    }

    private static Map<InventoryResource, Integer> createUpkeepCost() {
        Map<InventoryResource, Integer> upkeep = new HashMap<>();
        upkeep.put(InventoryResource.WOOD, Constants.LUMBER_MILL_WOOD_UPKEEP);
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
                InventoryResource.WOOD,
                Constants.LUMBER_MILL_HP
        );

        if (location.getTerrain() != TerrainType.FOREST) {
            throw new IllegalArgumentException("Lumber Mill can only be built on a FOREST hex.");
        }
    }
}