package model.map.hex;

import config.Constants;
import java.util.Random;

public enum TerrainType {
    FOREST(Constants.FOREST_MOVEMENT_COST, Constants.FOREST_RESOURCE_PROBABILITY, Constants.FOREST_RESOURCE_CAPACITY, HexResource.WOOD),
    PLAIN(Constants.PLAIN_MOVEMENT_COST, Constants.PLAIN_RESOURCE_PROBABILITY, Constants.PLAIN_RESOURCE_CAPACITY, HexResource.LIVESTOCK), // dasht (heyvun dare)
    MOUNTAIN(Constants.MOUNTAIN_MOVEMENT_COST, Constants.MOUNTAIN_RESOURCE_PROBABILITY, Constants.MOUNTAIN_RESOURCE_CAPACITY, HexResource.STONE, HexResource.IRON),
    MEADOW(Constants.MEADOW_MOVEMENT_COST, Constants.MEADOW_RESOURCE_PROBABILITY, Constants.MEADOW_RESOURCE_CAPACITY, HexResource.CROPS), // sabze zar
    MOUNTAIN_RANGE(Constants.MOUNTAIN_RANGE_MOVEMENT_COST, 0.0, 0, HexResource.NONE), // movement cost is high, effectively unreachable.
    SEA(Constants.SEA_MOVEMENT_COST, Constants.SEA_RESOURCE_PROBABILITY, Constants.SEA_RESOURCE_CAPACITY, HexResource.FISH);

    private final int movementCost;
    private final double resourceProbability;
    private final int resourceCapacity;
    private final HexResource[] possibleResources;

    TerrainType(int movementCost, double resourceProbability, int resourceCapacity, HexResource... possibleResources) {
        this.movementCost = movementCost;
        this.resourceProbability = resourceProbability;
        this.resourceCapacity = resourceCapacity;
        this.possibleResources = possibleResources;
    }

    public int getMovementCost() {
        return movementCost;
    }

    public int getResourceCapacity() {
        return resourceCapacity;
    }

    public HexResource generateResource(Random random) {
        if (possibleResources.length == 0 || possibleResources[0] == HexResource.NONE) {
            return HexResource.NONE;
        }

        if (random.nextDouble() < resourceProbability) {
            return possibleResources[random.nextInt(possibleResources.length)];
        }

        return HexResource.NONE;
    }
}