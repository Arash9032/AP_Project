package model.map;

import model.map.building.townhall.InventoryResource;

import java.util.Map;

public interface Maintainable extends Destructible {

    Map<InventoryResource, Integer> getUpkeepCost();
    int getConsecutiveUnpaidUpkeep();
    void setConsecutiveUnpaidUpkeep(int consecutiveUnpaidUpkeep);

    default void checkUpkeepPayment(boolean isPaid) {
        if (isPaid) {
            setConsecutiveUnpaidUpkeep(0);
        } else {
            setConsecutiveUnpaidUpkeep(getConsecutiveUnpaidUpkeep() + 1);
        }
    }

    @Override
    default boolean isDestroyed() {
        return Destructible.super.isDestroyed() || getConsecutiveUnpaidUpkeep() >= 3;
    }
}
