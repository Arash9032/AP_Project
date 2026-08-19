package model.map.building;

import config.Constants;
import model.map.CostBuilder;
import model.map.building.production.*;
import model.map.building.townhall.InventoryResource;
import model.map.building.townhall.TownHall;
import model.map.hex.Hex;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public enum BuildingType {

    LUMBER_MILL(
            Constants.LUMBER_MILL_CONSTRUCTION_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.LUMBER_MILL_WOOD_COST).build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.LUMBER_MILL_WOOD_UPKEEP).build(),
            LumberMill::new
            ),
    FARM(
            Constants.FARM_CONSTRUCTION_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.FARM_WOOD_COST).build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.FARM_WOOD_UPKEEP).build(),
            Farm::new
    ),
    STONE_MINE(
            Constants.STONE_MINE_CONSTRUCTION_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.STONE_MINE_WOOD_COST).build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.STONE_MINE_WOOD_UPKEEP).build(),
            StoneMine::new
    ),
    IRON_MINE(
            Constants.IRON_MINE_CONSTRUCTION_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.IRON_MINE_WOOD_COST).build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.IRON_MINE_WOOD_UPKEEP).build(),
            IronMine::new
    ),
    STABLE(
            Constants.STABLE_CONSTRUCTION_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.STABLE_WOOD_COST).build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.STABLE_WOOD_UPKEEP).build(),
            Stable::new
    ),
    VILLAGE(
            Constants.VILLAGE_CONSTRUCTION_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.VILLAGE_WOOD_COST)
                    .add(InventoryResource.STONE , Constants.VILLAGE_STONE_COST)
                    .add(InventoryResource.IRON , Constants.VILLAGE_IRON_COST)
                    .build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.VILLAGE_WOOD_UPKEEP)
                    .add(InventoryResource.STONE , Constants.VILLAGE_STONE_UPKEEP)
                    .add(InventoryResource.IRON , Constants.VILLAGE_IRON_UPKEEP)
                    .build(),
            Village::new
    ),
    TOWN(
            Constants.TOWN_CONSTRUCTION_AP_COST,
            new CostBuilder().add(InventoryResource.WOOD , Constants.TOWN_WOOD_COST)
                    .add(InventoryResource.STONE , Constants.TOWN_STONE_COST)
                    .add(InventoryResource.IRON , Constants.TOWN_IRON_COST)
                    .build(),
            new CostBuilder().add(InventoryResource.WOOD , Constants.TOWN_WOOD_UPKEEP)
                    .add(InventoryResource.STONE , Constants.TOWN_STONE_UPKEEP)
                    .add(InventoryResource.IRON , Constants.TOWN_IRON_UPKEEP)
                    .build(),
            Town::new
    ),
    TOWN_HALL(
            0,
            Collections.emptyMap(),
            Collections.emptyMap(),
            TownHall::new
    );

    private final int constructionApCost;
    private final Map<InventoryResource , Integer> constructionCost;
    private final Map<InventoryResource , Integer> upkeepCost;
    private final Function<Hex , Building> constructor;

    BuildingType(
            int constructionApCost,
            Map<InventoryResource, Integer> constructionCost,
            Map<InventoryResource, Integer> upkeepCost,
            Function<Hex , Building> constructor
    ) {
        this.constructionApCost = constructionApCost;
        this.constructionCost = constructionCost;
        this.upkeepCost = upkeepCost;
        this.constructor = constructor;
    }

    public int getConstructionApCost() {
        return constructionApCost;
    }

    public Building createBuilding(Hex location){
        Building building = constructor.apply(location);
        location.setBuilding(building);
        return building;
    }

    public Map<InventoryResource, Integer> getConstructionCost() {
        return constructionCost;
    }

    public Map<InventoryResource, Integer> getUpkeepCost() {
        return upkeepCost;
    }
}