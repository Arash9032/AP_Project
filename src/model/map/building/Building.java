package model.map.building;

import model.map.hex.Hex;
import model.map.hex.ResourceType;

import java.util.Map;

public abstract class Building {
    protected Hex location;
    protected final int constructionAPCost;
    protected final Map<ResourceType, Integer> constructionCost;
    protected final Map<ResourceType, Integer> upkeepCost;
    protected int consecutiveUnpaidUpkeep;
    protected int HP = 100;

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

    public int getConsecutiveUnpaidUpkeep() {
        return consecutiveUnpaidUpkeep;
    }

    public void setConsecutiveUnpaidUpkeep(int consecutiveUnpaidUpkeep) {
        this.consecutiveUnpaidUpkeep = consecutiveUnpaidUpkeep;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
