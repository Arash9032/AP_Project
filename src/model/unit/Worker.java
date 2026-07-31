package model.unit;

import config.Constants;
import model.map.hex.Hex;

public class Worker extends Unit {
    private boolean isStationed;

    public Worker(Hex startingHex) {
        super(startingHex, Constants.WORKER_MAX_AP, Constants.WORKER_VISION_RADIUS, UnitType.WORKER, Constants.WORKER_MAX_HP);
        this.isStationed = false;
    }

    public void setStationed(boolean stationed) {
        this.isStationed = stationed;
    }

    public boolean isStationed() {
        return isStationed;
    }

    @Override
    public int getMaximumHp() {
        return Constants.WORKER_MAX_HP;
    }
}
