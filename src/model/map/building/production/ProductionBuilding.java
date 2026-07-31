package model.map.building.production;

import model.map.building.Building;
import model.map.building.BuildingType;
import model.map.building.townhall.InventoryResource;
import model.map.hex.Hex;
import model.unit.Worker;

import java.util.ArrayList;
import java.util.List;

public abstract class ProductionBuilding extends Building {
    private final int workerCapacity;
    private final List<Worker> stationedWorkers;
    private int baseProductionRate;
    private final InventoryResource producedResource;

    public ProductionBuilding(
            Hex location,
            int workerCapacity,
            int baseProductionRate,
            InventoryResource producedResource,
            int HP,
            BuildingType type
    ) {
        super(location, HP, type);
        this.workerCapacity = workerCapacity;
        this.baseProductionRate = baseProductionRate;
        this.producedResource = producedResource;
        this.stationedWorkers = new ArrayList<>();
    }

    public boolean addWorker(Worker worker) {
        if (stationedWorkers.size() < workerCapacity && !worker.isStationed()) {
            stationedWorkers.add(worker);
            worker.setStationed(true);
            return true;
        }
        return false;
    }

    public boolean removeWorker(Worker worker) {
        if (stationedWorkers.remove(worker)) {
            worker.setStationed(false);
            return true;
        }
        return false;
    }

    public int calculateProduction() {
        return stationedWorkers.size() * baseProductionRate;
    }

    public void setBaseProductionRate(int baseProductionRate) {
        this.baseProductionRate = baseProductionRate;
    }

    public int getBaseProductionRate() {
        return baseProductionRate;
    }

    public int getWorkerCapacity() {
        return workerCapacity;
    }

    public List<Worker> getStationedWorkers() {
        return stationedWorkers;
    }

    public InventoryResource getProducedResource() {
        return producedResource;
    }
}