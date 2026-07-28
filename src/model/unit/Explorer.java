package model.unit;

import config.Constants;
import model.map.hex.Hex;

public class Explorer extends Unit {
    public Explorer(Hex startingHex) {
        super(startingHex, Constants.EXPLORER_MAX_AP, Constants.EXPLORER_VISION_RADIUS, UnitType.EXPLORER);
    }
}