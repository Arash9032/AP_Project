package model.map.building;

import config.Constants;
import model.map.hex.Hex;

public class Town extends Building {
    public Town(Hex location) {
        super(
                location,
                Constants.TOWN_MAXIMUM_HP,
                BuildingType.TOWN
        );
    }

    @Override
    public int getMaximumHP() {
        return Constants.TOWN_MAXIMUM_HP;
    }
}
