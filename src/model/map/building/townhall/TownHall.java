package model.map.building;

import java.util.HashMap;
import java.util.Map;
import config.Constants;
import model.ProductionTask;
import model.map.hex.Hex;
import model.map.hex.ResourceType;

public class TownHall extends Building {

    private final Map<ResourceType, Integer> resources;
    private int storageCapacity;
    private int unitCap;

    private ProductionTask activeTask;

    public TownHall(Hex location) {
        super(location, 0, new HashMap<>(), new HashMap<>());
        this.resources = new HashMap<>();
        this.storageCapacity = Constants.INITIAL_STORAGE_CAPACITY;
        this.unitCap = Constants.INITIAL_UNIT_CAP;

        resources.put(ResourceType.CROPS, Constants.INITIAL_CROPS);
        resources.put(ResourceType.WOOD, Constants.INITIAL_WOOD);
        resources.put(ResourceType.STONE, Constants.INITIAL_STONE);
        resources.put(ResourceType.IRON, Constants.INITIAL_IRON);
    }

    public void addResource(ResourceType type, int amount) {
        int current = resources.getOrDefault(type, 0);
        int newAmount = Math.min(current + amount, storageCapacity);
        resources.put(type, newAmount);
    }

    public boolean consumeResource(ResourceType type, int amount) {
        int current = resources.getOrDefault(type, 0);
        if (current >= amount) {
            resources.put(type, current - amount);
            return true;
        }
        return false;
    }

    public void applySafeguardProduction() {
        addResource(ResourceType.WOOD, Constants.SAFEGUARD_WOOD_PRODUCTION);
        addResource(ResourceType.CROPS, Constants.SAFEGUARD_CROPS_PRODUCTION);
    }

    public ProductionTask getActiveTask() {
        return activeTask;
    }

    public void setActiveTask(ProductionTask activeTask) {
        this.activeTask = activeTask;
    }

    public void cancelActiveTask(){
        this.activeTask = null;
    }

    public void processActiveTask() {
        if (activeTask != null) {
            activeTask.advanceTurn();
            if (activeTask.isComplete()) {
                activeTask = null;
            }
        }
    }

    public int getResourceAmount(ResourceType type) { return resources.getOrDefault(type, 0); }

    public int getStorageCapacity() { return storageCapacity; }
    public void increaseStorageCapacity(int amount) { this.storageCapacity += amount; }

    public int getUnitCap() { return unitCap; }
    public void increaseUnitCap(int amount) { this.unitCap += amount; }

    public Map<ResourceType, Integer> getResources() {
        return resources;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public void setUnitCap(int unitCap) {
        this.unitCap = unitCap;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
