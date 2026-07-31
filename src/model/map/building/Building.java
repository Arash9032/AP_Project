package model.map.building;

import model.map.Maintainable;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;

import java.util.Map;

public abstract class Building implements Maintainable {
    private final Hex location;
    private int consecutiveUnpaidUpkeep;
    private int HP;
    private final BuildingType type;

    public Building(Hex location, int HP , BuildingType type) {
        this.location = location;
        this.consecutiveUnpaidUpkeep = 0;
        this.HP = HP;
        this.type = type;
    }

    public Hex getLocation() {
        return location;
    }

    @Override
    public Map<InventoryResource, Integer> getUpkeepCost() {
        return type.getUpkeepCost();
    }

    @Override
    public int getConsecutiveUnpaidUpkeep() {
        return consecutiveUnpaidUpkeep;
    }

    @Override
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

    public BuildingType getType() {
        return type;
    }

}
