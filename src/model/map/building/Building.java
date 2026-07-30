package model.map.building;

import model.map.Destructible;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;

import java.util.Map;

public abstract class Building implements Destructible {
    private final Hex location;
    private final int constructionAPCost;
    private final Map<InventoryResource, Integer> constructionCost;
    private final Map<InventoryResource, Integer> upkeepCost;
    private int consecutiveUnpaidUpkeep;
    private int HP;

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

    @Override
    public int getHP() {
        return HP;
    }

    @Override
    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public void damage(int amount) {
        HP = Math.max(0 , HP - amount);
    }
}
