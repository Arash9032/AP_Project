package model.building;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import config.Constants;
import model.ProductionTask;
import model.hex.Hex;
import model.hex.ResourceType;

public class TownHall extends Building {

    private final Map<ResourceType, Integer> resources;
    private int storageCapacity;
    private int unitCap;

    private final Queue<ProductionTask> productionQueue;

    public TownHall(Hex location) {
        super(location, 0, new HashMap<>(), new HashMap<>());
        this.resources = new HashMap<>();
        this.storageCapacity = Constants.INITIAL_STORAGE_CAPACITY;
        this.unitCap = Constants.INITIAL_UNIT_CAP;

        this.productionQueue = new LinkedList<>();

        resources.put(ResourceType.FOOD, Constants.INITIAL_FOOD);
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
        addResource(ResourceType.FOOD, Constants.SAFEGUARD_FOOD_PRODUCTION);
    }

    public void addToQueue(ProductionTask task) {
        productionQueue.add(task);
    }

    public void processQueue() {
        ProductionTask currentTask = productionQueue.peek();
        if (currentTask != null) {
            currentTask.advanceTurn();
            if (currentTask.isComplete()) {
                productionQueue.poll();
            }
        }
    }

    public Queue<ProductionTask> getProductionQueue() { return productionQueue; }

    public int getResourceAmount(ResourceType type) { return resources.getOrDefault(type, 0); }

    public int getStorageCapacity() { return storageCapacity; }
    public void increaseStorageCapacity(int amount) { this.storageCapacity += amount; }

    public int getUnitCap() { return unitCap; }
    public void increaseUnitCap(int amount) { this.unitCap += amount; }
}
