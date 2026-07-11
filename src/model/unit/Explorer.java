package model.unit;

import config.Constants;
import model.hex.Hex;

public class Explorer extends Unit {
    public Explorer(Hex startingHex) {
        super(startingHex, Constants.EXPLORER_MAX_AP, Constants.EXPLORER_VISION_RADIUS);
    }

    @Override
    public int getTurnCost() {
        return Constants.EXPLORER_TURN_COST;
    }

    @Override
    public String getName() {
        return "Explorer";
    }
}