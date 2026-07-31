package model.map.building;

import model.map.Maintainable;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;

import java.util.Map;

public abstract class Building implements Maintainable {
    private final Hex location;
    private int consecutiveUnpaidUpkeep;
    private int hp;
    private final BuildingType type;

    public Building(Hex location, int hp, BuildingType type) {
        this.location = location;
        this.consecutiveUnpaidUpkeep = 0;
        this.hp = hp;
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
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    public BuildingType getType() {
        return type;
    }

}
