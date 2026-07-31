package model.map.edge;

import config.Constants;
import model.map.CostBuilder;
import model.map.Maintainable;
import model.map.building.townhall.InventoryResource;

import java.util.Map;

public class Wall implements Maintainable {

    private static final Map<InventoryResource , Integer> constructionCost = new CostBuilder()
            .add(InventoryResource.WOOD , Constants.WALL_WOOD_COST)
            .add(InventoryResource.STONE , Constants.WALL_STONE_COST)
            .build();

    private static final Map<InventoryResource , Integer> upkeepCost = new CostBuilder()
            .add(InventoryResource.WOOD , Constants.WALL_WOOD_UPKEEP)
            .add(InventoryResource.STONE , Constants.WALL_STONE_UPKEEP)
            .build();

    private int HP;
    private boolean isFriendly; // true: for player // false: for game
    private int consecutiveUnpaidUpkeep;

    public Wall(boolean isFriendly) {
        this.isFriendly = isFriendly;
        HP = Constants.WALL_MAXIMUM_HP;
        consecutiveUnpaidUpkeep = 0;
    }

    @Override
    public int getMaximumHp() {
        return Constants.WALL_MAXIMUM_HP;
    }

    @Override
    public int getHp() {
        return HP;
    }

    @Override
    public void setHp(int hp) {
        this.HP = hp;
    }

    public boolean isFriendly() {
        return isFriendly;
    }

    public void setFriendly(boolean friendly) {
        isFriendly = friendly;
    }

    @Override
    public Map<InventoryResource, Integer> getUpkeepCost() {
        return upkeepCost;
    }

    @Override
    public int getConsecutiveUnpaidUpkeep() {
        return consecutiveUnpaidUpkeep;
    }

    @Override
    public void setConsecutiveUnpaidUpkeep(int consecutiveUnpaidUpkeep) {
        this.consecutiveUnpaidUpkeep = consecutiveUnpaidUpkeep;
    }

    public static Map<InventoryResource , Integer> getConstructionCost(){
        return constructionCost;
    }

    public static int getConstructionApCost(){
        return Constants.WALL_CONSTRUCTION_AP_COST;
    }
}
