package model;

import model.map.Destructible;
import model.map.GameMap;
import model.map.Maintainable;
import model.map.building.Building;
import model.unit.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GameState {
    private GameMap gameMap;
    private final List<Unit> units;
    private final List<Building> buildings;
    private final List<Destructible> destructibles;
    private final List<Maintainable> maintainables;

    public GameState() {
        units = new ArrayList<>();
        buildings = new ArrayList<>();
        destructibles = new ArrayList<>();
        maintainables = new ArrayList<>();
    }

    public GameState(GameMap gameMap){
        this();
        this.gameMap = gameMap;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public List<Unit> getUnits() {
        return Collections.unmodifiableList(units);
    }

    public List<Building> getBuildings() {
        return Collections.unmodifiableList(buildings);
    }

    public List<Destructible> getDestructibles() {
        return Collections.unmodifiableList(destructibles);
    }

    public List<Maintainable> getMaintainables() {
        return Collections.unmodifiableList(maintainables);
    }

    public void addUnit(Unit unit){
        if(unit == null) return;
        units.add(unit);
        destructibles.add(unit);
    }

    public void addBuilding(Building building){
        if(building == null) return;
        buildings.add(building);
        maintainables.add(building);
        destructibles.add(building);
    }

    public void removeUnit(Unit unit){
        if(unit == null) return;
        units.remove(unit);
        destructibles.remove(unit);
    }

    public void removeBuilding(Building building){
        if(building == null) return;
        buildings.remove(building);
        maintainables.remove(building);
        destructibles.remove(building);
    }
}
