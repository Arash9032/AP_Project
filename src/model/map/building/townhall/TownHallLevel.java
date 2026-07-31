package model.map.building.townhall;

import config.Constants;
import model.map.CostBuilder;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public enum TownHallLevel {
    LEVEL_1(
            1,
            "Base Camp",
            Constants.LEVEL_1_TOWN_HALL_STORAGE_CAPACITY,
            Constants.LEVEL_1_TOWN_HALL_MILITARY_UNIT_CAPACITY,
            0,
            0,
            Collections.emptyMap()
    ),
    LEVEL_2(
            2,
            "Settlement",
            Constants.LEVEL_2_TOWN_HALL_STORAGE_CAPACITY,
            Constants.LEVEL_2_TOWN_HALL_MILITARY_UNIT_CAPACITY,
            Constants.LEVEL_2_TOWN_HALL_UPGRADE_TURN_COST,
            Constants.LEVEL_2_TOWN_HALL_UPGRADE_HEAL_AMOUNT,
            new CostBuilder()
                    .add(InventoryResource.WOOD , Constants.LEVEL_2_TOWN_HALL_UPGRADE_WOOD_COST)
                    .add(InventoryResource.STONE , Constants.LEVEL_2_TOWN_HALL_UPGRADE_STONE_COST)
                    .build()
    ),
    LEVEL_3(
            3,
            "Capital",
            Constants.LEVEL_3_TOWN_HALL_STORAGE_CAPACITY,
            Constants.LEVEL_3_TOWN_HALL_MILITARY_UNIT_CAPACITY,
            Constants.LEVEL_3_TOWN_HALL_UPGRADE_TURN_COST,
            Constants.LEVEL_3_TOWN_HALL_UPGRADE_HEAL_AMOUNT,
            new CostBuilder()
                    .add(InventoryResource.STONE , Constants.LEVEL_3_TOWN_HALL_UPGRADE_STONE_COST)
                    .add(InventoryResource.IRON , Constants.LEVEL_3_TOWN_HALL_UPGRADE_IRON_COST)
                    .build()
    );

    private static final TownHallLevel[] levels = TownHallLevel.values();

    public TownHallLevel getNextLevel(){
        int nextIndex = this.ordinal() + 1;
        if(nextIndex >= levels.length) return null;
        return levels[nextIndex];
    }

    private final int levelNumber;
    private final String levelName;
    private final int storageCapacity;
    private final int militaryUnitCapacity;
    private final int upgradeTurnCost;
    private final int upgradeHealAmount;
    private final Map<InventoryResource , Integer> upgradeCost;

    TownHallLevel(
            int levelNumber,
            String levelName,
            int storageCapacity,
            int militaryUnitCapacity,
            int upgradeTurnCost,
            int upgradeHealAmount,
            Map<InventoryResource , Integer> upgradeCost
    ) {
        this.levelNumber = levelNumber;
        this.levelName = levelName;
        this.storageCapacity = storageCapacity;
        this.militaryUnitCapacity = militaryUnitCapacity;
        this.upgradeTurnCost = upgradeTurnCost;
        this.upgradeHealAmount = upgradeHealAmount;
        this.upgradeCost = upgradeCost;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public String getLevelName() {
        return levelName;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public int getMilitaryUnitCapacity() {
        return militaryUnitCapacity;
    }

    public Map<InventoryResource, Integer> getUpgradeCost() {
        return upgradeCost;
    }

    public int getUpgradeTurnCost() {
        return upgradeTurnCost;
    }

    public int getUpgradeHealAmount() {
        return upgradeHealAmount;
    }
}
