package model.unit;

import config.Constants;
import model.map.hex.Hex;

public class BorderExpander extends Unit {
    private boolean isConsumed;

    public BorderExpander(Hex startingHex) {
        super(startingHex, Constants.BORDER_EXPANDER_MAX_AP, Constants.BORDER_EXPANDER_VISION_RADIUS, UnitType.BORDER_EXPANDER);
        this.isConsumed = false;
    }

    public void consume() {
        this.isConsumed = true;
    }

    public boolean isConsumed() {
        return isConsumed;
    }
}
