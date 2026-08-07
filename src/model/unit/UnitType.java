package model.unit;

import config.Constants;
import model.map.CostBuilder;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;

import java.util.Map;

public enum UnitType {

    WORKER(
            Constants.WORKER_PRODUCTION_TURN_COST,
            new CostBuilder().add(InventoryResource.FOOD, Constants.WORKER_FOOD_COST).build()
    ) {
        @Override
        public Unit createUnit(Hex location) {
            return new Worker(location);
        }
    },
    BUILDER(
            Constants.BUILDER_PRODUCTION_TURN_COST,
            new CostBuilder().add(InventoryResource.FOOD, Constants.BUILDER_FOOD_COST).build()
    ) {
        @Override
        public Unit createUnit(Hex location) {
            return new Builder(location);
        }
    },
    EXPLORER(
            Constants.EXPLORER_PRODUCTION_TURN_COST,
            new CostBuilder().add(InventoryResource.FOOD, Constants.EXPLORER_FOOD_COST).build()
    ) {
        @Override
        public Unit createUnit(Hex location) {
            return new Explorer(location);
        }
    },
    BORDER_EXPANDER(
            Constants.BORDER_EXPANDER_PRODUCTION_TURN_COST,
            new CostBuilder()
                    .add(InventoryResource.FOOD, Constants.BORDER_EXPANDER_FOOD_COST)
                    .add(InventoryResource.WOOD, Constants.BORDER_EXPANDER_WOOD_COST)
                    .build()
    ) {
        @Override
        public Unit createUnit(Hex location) {
            return new BorderExpander(location);
        }
    };

    private final int productionTurnCost;
    private final Map<InventoryResource, Integer> productionCost;

    UnitType(int productionTurnCost, Map<InventoryResource, Integer> productionCost) {
        this.productionTurnCost = productionTurnCost;
        this.productionCost = productionCost;
    }

    public int getProductionTurnCost() {
        return productionTurnCost;
    }

    public Map<InventoryResource, Integer> getProductionCost() {
        return productionCost;
    }

    public abstract Unit createUnit(Hex location);
}