package model.map.building.production;

import java.util.HashMap;
import java.util.Map;
import config.Constants;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;
import model.map.hex.HexResource;
import model.map.hex.TerrainType;

public class Stable extends ProductionBuilding {

    private static Map<InventoryResource, Integer> createConstructionCost() {
        Map<InventoryResource, Integer> cost = new HashMap<>();
        cost.put(InventoryResource.WOOD, Constants.STABLE_WOOD_COST);
        return cost;
    }

    private static Map<InventoryResource, Integer> createUpkeepCost() {
        Map<InventoryResource, Integer> upkeep = new HashMap<>();
        upkeep.put(InventoryResource.WOOD, Constants.STABLE_WOOD_UPKEEP);
        return upkeep;
    }

    public Stable(Hex location) {
        super(
                location,
                Constants.STABLE_AP_COST,
                createConstructionCost(),
                createUpkeepCost(),
                Constants.STABLE_CAPACITY,
                Constants.STABLE_RATE,
                InventoryResource.FOOD,
                Constants.STABLE_MAXIMUM_HP
        );

        if (location.getTerrain() != TerrainType.PLAIN || location.getResource() != HexResource.LIVESTOCK) {
            throw new IllegalArgumentException("Stable requires a PLAIN hex containing LIVESTOCK.");
        }
    }

    @Override
    public int getMaximumHP() {
        return Constants.STABLE_MAXIMUM_HP;
    }
}
