package model.map;

import model.map.building.townhall.InventoryResource;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public final class CostBuilder {
    private final Map<InventoryResource , Integer> cost = new EnumMap<>(InventoryResource.class);

    public CostBuilder add(InventoryResource resource , int amount){
        cost.put(resource , amount);
        return this;
    }

    public Map<InventoryResource , Integer> build(){
        return Collections.unmodifiableMap(cost);
    }
}
