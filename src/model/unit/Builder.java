package model.unit;

import config.Constants;
import model.map.hex.Hex;

public class Builder extends Unit {
    private int charges;

    public Builder(Hex startingHex) {
        super(startingHex, Constants.BUILDER_MAX_AP, Constants.BUILDER_VISION_RADIUS, UnitType.BUILDER, Constants.BUILDER_MAX_HP);
        this.charges = Constants.BUILDER_INITIAL_CHARGES;
    }

    public void consumeCharge() {
        this.charges--;
    }

    public int getCharges() {
        return charges;
    }

    public boolean isConsumed() {
        return charges <= 0;
    }

    @Override
    public int getMaximumHP() {
        return Constants.BUILDER_MAX_HP;
    }
}
