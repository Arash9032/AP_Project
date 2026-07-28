package model.map.building;

import config.Constants;
import model.map.building.production.*;
import model.map.hex.Hex;

public enum BuildingType {

    LUMBER_MILL(Constants.LUMBER_MILL_AP_COST) {
        @Override
        public Building createBuilding(Hex location) {
            return new LumberMill(location);
        }
    },
    FARM(Constants.FARM_AP_COST) {
        @Override
        public Building createBuilding(Hex location) {
            return new Farm(location);
        }
    },
    STONE_MINE(Constants.STONE_MINE_AP_COST) {
        @Override
        public Building createBuilding(Hex location) {
            return new StoneMine(location);
        }
    },
    IRON_MINE(Constants.IRON_MINE_AP_COST) {
        @Override
        public Building createBuilding(Hex location) {
            return new IronMine(location);
        }
    },
    STABLE(Constants.STABLE_AP_COST) {
        @Override
        public Building createBuilding(Hex location) {
            return new Stable(location);
        }
    },
    VILLAGE(Constants.VILLAGE_AP_COST) {
        @Override
        public Building createBuilding(Hex location) {
            return new Village(location);
        }
    },
    TOWN(Constants.TOWN_AP_COST) {
        @Override
        public Building createBuilding(Hex location) {
            return new Town(location);
        }
    };

    private final int apCost;

    BuildingType(int apCost) {
        this.apCost = apCost;
    }

    public int getApCost() {
        return apCost;
    }

    public abstract Building createBuilding(Hex location);
}