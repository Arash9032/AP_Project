package model.map.building;

import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;
import model.map.hex.HexResource;

import java.util.Map;

public abstract class Building {
    protected Hex location;
    protected final int constructionAPCost;
    protected final Map<InventoryResource, Integer> constructionCost;
    protected final Map<InventoryResource, Integer> upkeepCost;
    protected int consecutiveUnpaidUpkeep;
    protected int HP;

    public Building(Hex location, int constructionAPCost, Map<InventoryResource, Integer> constructionCost, Map<InventoryResource, Integer> upkeepCost , int HP) {
        this.location = location;
        this.constructionAPCost = constructionAPCost;
        this.constructionCost = constructionCost;
        this.upkeepCost = upkeepCost;
        this.consecutiveUnpaidUpkeep = 0;
        this.HP = HP;
    }

    public void processUpkeep(boolean isPaid) {
        if (isPaid) {
            consecutiveUnpaidUpkeep = 0;
        } else {
            consecutiveUnpaidUpkeep++;
        }
    }

    public boolean isDestroyed() {
        return (consecutiveUnpaidUpkeep >= 3 || HP <= 0);
    }

    public Hex getLocation() {
        return location;
    }

    public int getConstructionAPCost() {
        return constructionAPCost;
    }

    public Map<InventoryResource, Integer> getConstructionCost() {
        return constructionCost;
    }

    public Map<InventoryResource, Integer> getUpkeepCost() {
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
