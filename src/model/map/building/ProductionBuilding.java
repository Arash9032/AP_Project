package model.map.building;

import model.map.hex.Hex;
import model.map.hex.ResourceType;
import model.unit.Worker;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public abstract class ProductionBuilding extends Building {
    protected final int workerCapacity;
    protected List<Worker> stationedWorkers;
    protected int baseProductionRate;
    protected final ResourceType producedResource;

    public ProductionBuilding(Hex location, int constructionAPCost, Map<ResourceType, Integer> constructionCost, Map<ResourceType, Integer> upkeepCost, int workerCapacity, int baseProductionRate, ResourceType producedResource) {
        super(location, constructionAPCost, constructionCost, upkeepCost);
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

    public void increaseProductionRate(int amount) {
        this.baseProductionRate += amount;
    }

    public void setProductionRate(int newRate) {
        this.baseProductionRate = newRate;
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

    public ResourceType getProducedResource() {
        return producedResource;
    }
}