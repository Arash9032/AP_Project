package model.building;

import model.hex.Hex;
import model.hex.ResourceType;

import java.util.Map;

public abstract class Building {
    protected Hex location;
    protected final int constructionAPCost;
    protected final Map<ResourceType, Integer> constructionCost;
    protected final Map<ResourceType, Integer> upkeepCost;
    protected int consecutiveUnpaidUpkeep;

    public Building(Hex location, int constructionAPCost, Map<ResourceType, Integer> constructionCost, Map<ResourceType, Integer> upkeepCost) {
        this.location = location;
        this.constructionAPCost = constructionAPCost;
        this.constructionCost = constructionCost;
        this.upkeepCost = upkeepCost;
        this.consecutiveUnpaidUpkeep = 0;
    }

    public void processUpkeep(boolean isPaid) {
        if (isPaid) {
            consecutiveUnpaidUpkeep = 0;
        } else {
            consecutiveUnpaidUpkeep++;
        }
    }

    public boolean isDestroyed() {
        return consecutiveUnpaidUpkeep >= 3;
    }

    public Hex getLocation() {
        return location;
    }

    public int getConstructionAPCost() {
        return constructionAPCost;
    }

    public Map<ResourceType, Integer> getConstructionCost() {
        return constructionCost;
    }

    public Map<ResourceType, Integer> getUpkeepCost() {
        return upkeepCost;
    }
}
