package model.hex;

import config.Constants;

public enum TerrainType {
    FOREST(Constants.FOREST_MOVEMENT_COST),
    PLAIN(Constants.PLAIN_MOVEMENT_COST), // dasht (heyvun dare)
    MOUNTAIN(Constants.MOUNTAIN_MOVEMENT_COST),
    MEADOW(Constants.MEADOW_MOVEMENT_COST); // sabze sar

    private final int movementCost;

    TerrainType(int movementCost) {
        this.movementCost = movementCost;
    }

    public int getMovementCost() {
        return movementCost;
    }
}