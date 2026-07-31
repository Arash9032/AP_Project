package model.map.building;

import config.Constants;
import model.map.hex.Hex;

public class Village extends Building {

    public Village(Hex location) {
        super(
                location,
                Constants.VILLAGE_MAXIMUM_HP,
                BuildingType.VILLAGE
        );
    }

    @Override
    public int getMaximumHp() {
        return Constants.VILLAGE_MAXIMUM_HP;
    }
}
