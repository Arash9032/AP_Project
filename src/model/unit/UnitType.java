package model.unit;

import config.Constants;
import model.map.hex.Hex;

public enum UnitType {

    WORKER(Constants.WORKER_PRODUCTION_TURN_COST) {
        @Override
        public Unit createUnit(Hex location) {
            return new Worker(location);
        }
    },
    BUILDER(Constants.BUILDER_PRODUCTION_TURN_COST) {
        @Override
        public Unit createUnit(Hex location) {
            return new Builder(location);
        }
    },
    EXPLORER(Constants.EXPLORER_PRODUCTION_TURN_COST) {
        @Override
        public Unit createUnit(Hex location) {
            return new Explorer(location);
        }
    },
    BORDER_EXPANDER(Constants.BORDER_EXPANDER_PRODUCTION_TURN_COST) {
        @Override
        public Unit createUnit(Hex location) {
            return new BorderExpander(location);
        }
    };

    private final int turnCost;

    UnitType(int turnCost) {
        this.turnCost = turnCost;
    }

    public int getTurnCost() {
        return turnCost;
    }

    public abstract Unit createUnit(Hex location);
}