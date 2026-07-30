package model.map.building;

import config.Constants;
import model.map.CostBuilder;
import model.map.building.production.*;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;

import java.util.Map;

public enum BuildingType {

    LUMBER_MILL(
            Constants.LUMBER_MILL_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.LUMBER_MILL_WOOD_COST).build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.LUMBER_MILL_WOOD_UPKEEP).build()
            ) {
        @Override
        public Building createBuilding(Hex location) {
            return new LumberMill(location);
        }
    },
    FARM(
            Constants.FARM_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.FARM_WOOD_COST).build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.FARM_WOOD_UPKEEP).build()
    ) {
        @Override
        public Building createBuilding(Hex location) {
            return new Farm(location);
        }
    },
    STONE_MINE(
            Constants.STONE_MINE_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.STONE_MINE_WOOD_COST).build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.STONE_MINE_WOOD_UPKEEP).build()
    ) {
        @Override
        public Building createBuilding(Hex location) {
            return new StoneMine(location);
        }
    },
    IRON_MINE(
            Constants.IRON_MINE_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.IRON_MINE_WOOD_COST).build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.IRON_MINE_WOOD_UPKEEP).build()
    ) {
        @Override
        public Building createBuilding(Hex location) {
            return new IronMine(location);
        }
    },
    STABLE(
            Constants.STABLE_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.STABLE_WOOD_COST).build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.STABLE_WOOD_UPKEEP).build()
    ) {
        @Override
        public Building createBuilding(Hex location) {
            return new Stable(location);
        }
    },
    VILLAGE(
            Constants.VILLAGE_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.VILLAGE_WOOD_COST)
                    .add(InventoryResource.STONE , Constants.VILLAGE_STONE_COST)
                    .add(InventoryResource.IRON , Constants.VILLAGE_IRON_COST)
                    .build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.VILLAGE_WOOD_UPKEEP)
                    .add(InventoryResource.STONE , Constants.VILLAGE_STONE_UPKEEP)
                    .add(InventoryResource.IRON , Constants.VILLAGE_IRON_UPKEEP)
                    .build()
    ) {
        @Override
        public Building createBuilding(Hex location) {
            return new Village(location);
        }
    },
    TOWN(
            Constants.TOWN_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.TOWN_WOOD_COST)
                    .add(InventoryResource.STONE , Constants.TOWN_STONE_COST)
                    .add(InventoryResource.IRON , Constants.TOWN_IRON_COST)
                    .build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.TOWN_WOOD_UPKEEP)
                    .add(InventoryResource.STONE , Constants.TOWN_STONE_UPKEEP)
                    .add(InventoryResource.IRON , Constants.TOWN_IRON_UPKEEP)
                    .build()
    ) {
        @Override
        public Building createBuilding(Hex location) {
            return new Town(location);
        }
    };

    private final int apCost;
    private final Map<InventoryResource , Integer> constructionCost;
    private final Map<InventoryResource , Integer> upkeepCost;
    BuildingType(int apCost, Map<InventoryResource, Integer> constructionCost, Map<InventoryResource, Integer> upkeepCost) {
        this.apCost = apCost;
        this.constructionCost = constructionCost;
        this.upkeepCost = upkeepCost;
    }

    public int getApCost() {
        return apCost;
    }

    public abstract Building createBuilding(Hex location);

    public Map<InventoryResource, Integer> getConstructionCost() {
        return constructionCost;
    }

    public Map<InventoryResource, Integer> getUpkeepCost() {
        return upkeepCost;
    }
}