package model.map.building.townhall;

import config.Constants;
import model.command.TurnBasedProductionCommand;
import model.command.UpgradeTownHallCommand;
import model.map.building.Building;
import model.map.building.BuildingType;
import model.map.hex.Hex;

public class TownHall extends Building {

    private final TownHallInventory inventory;
    private TownHallLevel level;
    private int unitCap;
    private int maximumHp;

    private TurnBasedProductionCommand activeTask;

    public TownHall(Hex location) {
        super(location, Constants.TOWN_HALL_INITIAL_HP , BuildingType.TOWN_HALL);
        this.level = TownHallLevel.LEVEL_1;
        this.unitCap = Constants.INITIAL_UNIT_CAP;
        this.inventory = new TownHallInventory(level.getStorageCapacity());
        maximumHp = Constants.TOWN_HALL_INITIAL_HP;
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

    public TurnBasedProductionCommand getActiveTask() {
        return activeTask;
    }

    public void setActiveTask(TurnBasedProductionCommand activeTask) {
        this.activeTask = activeTask;
    }

    public int getResourceAmount(InventoryResource resource) {
        return inventory.getResourceAmount(resource);
    }

    public TownHallInventory getInventory() {
        return inventory;
    }

    public TownHallLevel getLevel() {
        return level;
    }

    public void setLevel(TownHallLevel level) {
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
    public int getMaximumHp() {
        return maximumHp;
    }

    public void setMaximumHp(int maximumHp) {
        this.maximumHp = maximumHp;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public void damage(int amount) {
        if(amount <= 0 ) return;
        setHp(Math.max(1 , getHp() - amount));
    }

    public void startUpgrade(){
        if(activeTask != null)
            throw new IllegalStateException("There already is an active task.");
        if(level.getNextLevel() == null) {
            throw new IllegalStateException("Town hall level is at its max.");
        }
        if(!inventory.consumeResources(level.getNextLevel().getUpgradeCost()))
            throw new IllegalStateException("Not enough resources to upgrade town hall.");
        activeTask = new UpgradeTownHallCommand(this, level.getNextLevel().getUpgradeTurnCost());
    }

    public void completeUpgrade(){
        TownHallLevel nextLevel = level.getNextLevel();
        if(nextLevel == null)
            throw new IllegalStateException("Town hall level is at its max.");
        level = nextLevel;
        setHp(Math.min(getHp() + level.getUpgradeHealAmount() , maximumHp));
        inventory.setCapacity(level.getStorageCapacity());
    }
}