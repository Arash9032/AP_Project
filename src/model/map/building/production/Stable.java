package model.map.building;

import java.util.HashMap;
import java.util.Map;
import config.Constants;
import model.map.hex.Hex;
import model.map.hex.HexResource;
import model.map.hex.TerrainType;

public class Stable extends Building {

    private static Map<HexResource, Integer> createConstructionCost() {
        Map<HexResource, Integer> cost = new HashMap<>();
        cost.put(HexResource.WOOD, Constants.STABLE_WOOD_COST);
        return cost;
    }

    private static Map<HexResource, Integer> createUpkeepCost() {
        Map<HexResource, Integer> upkeep = new HashMap<>();
        upkeep.put(HexResource.WOOD, Constants.STABLE_WOOD_UPKEEP);
        return upkeep;
    }

    public Stable(Hex location) {
        super(
                location,
                Constants.STABLE_AP_COST,
                createConstructionCost(),
                createUpkeepCost()
        );

        if (location.getTerrain() != TerrainType.PLAIN || location.getResource() != HexResource.LIVESTOCK) {
            throw new IllegalArgumentException("Stable requires a PLAIN hex containing FOOD (Cows/Sheep).");
        }
    }
}
