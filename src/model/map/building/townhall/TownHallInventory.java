package model.map.building.townhall;

import config.Constants;

import java.util.EnumMap;
import java.util.Map;

public class TownHallInventory {

    private final Map<InventoryResource, Integer> resources;
    private int capacity;

    public TownHallInventory(int initialCapacity) {
        this.capacity = initialCapacity;
        this.resources = new EnumMap<>(InventoryResource.class);

        resources.put(InventoryResource.FOOD, Constants.INITIAL_CROPS);
        resources.put(InventoryResource.WOOD, Constants.INITIAL_WOOD);
        resources.put(InventoryResource.STONE, Constants.INITIAL_STONE);
        resources.put(InventoryResource.IRON, Constants.INITIAL_IRON);
    }

    public void addResource(InventoryResource resource, int amount) {
        if (amount <= 0) return;
        int current = resources.getOrDefault(resource, 0);
        int updated = Math.min(current + amount, capacity);
        resources.put(resource, updated);
    }

    public boolean consumeResource(InventoryResource resource, int amount) {
        if (amount <= 0) return true;
        int current = resources.getOrDefault(resource, 0);
        if (current >= amount) {
            resources.put(resource, current - amount);
            return true;
        }
        return false;
    }

    public boolean hasEnoughResources(Map<InventoryResource, Integer> costs) {
        if (costs == null) return true;
        for (Map.Entry<InventoryResource, Integer> entry : costs.entrySet()) {
            if (getResourceAmount(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public boolean consumeResources(Map<InventoryResource, Integer> costs) {
        if(costs == null || costs.isEmpty()) return true;
        if (hasEnoughResources(costs)) {
            for (Map.Entry<InventoryResource, Integer> entry : costs.entrySet()) {
                consumeResource(entry.getKey(), entry.getValue());
            }
            return true;
        }
        return false;
    }

    public int getResourceAmount(InventoryResource resource) {
        return resources.getOrDefault(resource, 0);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void increaseCapacity(int amount) {
        if (amount > 0) {
            this.capacity += amount;
        }
    }

    public Map<InventoryResource, Integer> getResources() {
        return new EnumMap<>(resources);
    }
}