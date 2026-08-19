package model.unit;

import config.Constants;
import model.map.CostBuilder;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;

import java.util.Map;
import java.util.function.Function;

public enum UnitType {

    WORKER(
            Constants.WORKER_PRODUCTION_TURN_COST,
            new CostBuilder().add(InventoryResource.FOOD, Constants.WORKER_FOOD_COST).build(),
            Worker::new
    ),
    BUILDER(
            Constants.BUILDER_PRODUCTION_TURN_COST,
            new CostBuilder().add(InventoryResource.FOOD, Constants.BUILDER_FOOD_COST).build(),
            Builder::new
    ),
    EXPLORER(
            Constants.EXPLORER_PRODUCTION_TURN_COST,
            new CostBuilder().add(InventoryResource.FOOD, Constants.EXPLORER_FOOD_COST).build(),
            Explorer::new
    ),
    BORDER_EXPANDER(
            Constants.BORDER_EXPANDER_PRODUCTION_TURN_COST,
            new CostBuilder()
                    .add(InventoryResource.FOOD, Constants.BORDER_EXPANDER_FOOD_COST)
                    .add(InventoryResource.WOOD, Constants.BORDER_EXPANDER_WOOD_COST)
                    .build(),
            BorderExpander::new
    );

    private final int productionTurnCost;
    private final Map<InventoryResource, Integer> productionCost;
    private final Function<Hex , Unit> constructor;

    UnitType(int productionTurnCost, Map<InventoryResource, Integer> productionCost, Function<Hex, Unit> constructor) {
        this.productionTurnCost = productionTurnCost;
        this.productionCost = productionCost;
        this.constructor = constructor;
    }

    public int getProductionTurnCost() {
        return productionTurnCost;
    }

    public Map<InventoryResource, Integer> getProductionCost() {
        return productionCost;
    }

    public Unit createUnit(Hex location){
        return constructor.apply(location);
    }
}