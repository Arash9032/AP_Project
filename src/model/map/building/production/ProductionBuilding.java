package model.map.building;

import model.map.hex.Hex;
import model.map.hex.HexResource;
import model.unit.Worker;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public abstract class ProductionBuilding extends Building {
    protected final int workerCapacity;
    protected List<Worker> stationedWorkers;
    protected int baseProductionRate;
    protected final HexResource producedResource;

    public ProductionBuilding(Hex location, int constructionAPCost, Map<HexResource, Integer> constructionCost, Map<HexResource, Integer> upkeepCost, int workerCapacity, int baseProductionRate, HexResource producedResource , int HP) {
        super(location, constructionAPCost, constructionCost, upkeepCost , HP);
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

    public HexResource getProducedResource() {
        return producedResource;
    }
}