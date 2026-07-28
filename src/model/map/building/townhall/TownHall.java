package model.map.building.townhall;

import java.util.HashMap;
import config.Constants;
import model.command.ProductionCommand;
import model.map.building.Building;
import model.map.hex.Hex;

public class TownHall extends Building {

    private final TownHallInventory inventory;
    private int level;
    private int unitCap;

    private ProductionCommand activeTask;

    public TownHall(Hex location) {
        super(location, 0, new HashMap<>(), new HashMap<>(), Constants.TOWN_HALL_INITIAL_HP);
        this.level = 1;
        this.unitCap = Constants.INITIAL_UNIT_CAP;
        this.inventory = new TownHallInventory(Constants.INITIAL_STORAGE_CAPACITY);
    }

    public void addResource(InventoryResource resource, int amount) {
        inventory.addResource(resource, amount);
    }

    public boolean consumeResource(InventoryResource resource, int amount) {
        return inventory.consumeResource(resource, amount);
    }

    public void applySafeguardProduction() {
        inventory.addResource(InventoryResource.WOOD, Constants.SAFEGUARD_WOOD_PRODUCTION);
        inventory.addResource(InventoryResource.FOOD, Constants.SAFEGUARD_CROPS_PRODUCTION);
    }

    public ProductionCommand getActiveTask() {
        return activeTask;
    }

    public void setActiveTask(ProductionCommand activeTask) {
        this.activeTask = activeTask;
    }

    public int getResourceAmount(InventoryResource resource) {
        return inventory.getResourceAmount(resource);
    }

    public TownHallInventory getInventory() {
        return inventory;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUnitCap() {
        return unitCap;
    }

    public void increaseUnitCap(int amount) {
        this.unitCap += amount;
    }

    public void setUnitCap(int unitCap) {
        this.unitCap = unitCap;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    public void upgrade(){

    }
}