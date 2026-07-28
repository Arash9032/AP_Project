package model.unit;

import config.Constants;
import model.map.hex.Hex;

public class Worker extends Unit {
    private boolean isStationed;

    public Worker(Hex startingHex) {
        super(startingHex, Constants.WORKER_MAX_AP, Constants.WORKER_VISION_RADIUS);
        this.isStationed = false;
    }

    public void setStationed(boolean stationed) {
        this.isStationed = stationed;
    }

    public boolean isStationed() {
        return isStationed;
    }

    public int getTurnCost() {
        return Constants.WORKER_PRODUCTION_TURN_COST;
    }

    public String getName() {
        return "Worker";
    }
}
