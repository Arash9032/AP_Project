package model.unit;

import config.Constants;
import model.hex.Hex;

public class Builder extends Unit {
    private int charges;

    public Builder(Hex startingHex) {
        super(startingHex, Constants.BUILDER_MAX_AP, Constants.BUILDER_VISION_RADIUS);
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
}
