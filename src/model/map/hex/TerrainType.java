package model.map.hex;

import config.Constants;

public enum TerrainType {
    FOREST(Constants.FOREST_MOVEMENT_COST),
    PLAIN(Constants.PLAIN_MOVEMENT_COST), // dasht (heyvun dare)
    MOUNTAIN(Constants.MOUNTAIN_MOVEMENT_COST),
    MEADOW(Constants.MEADOW_MOVEMENT_COST), // sabze sar
    MOUNTAIN_RANGE(Constants.MOUNTAIN_RANGE_MOVEMENT_COST), // movement cost is high, effectively unreachable.
    SEA(Constants.SEA_MOVEMENT_COST);


    private final int movementCost;

    TerrainType(int movementCost) {
        this.movementCost = movementCost;
    }

    public int getMovementCost() {
        return movementCost;
    }
}