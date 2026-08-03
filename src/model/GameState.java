package model;

import model.map.Destructible;
import model.map.GameMap;
import model.map.Maintainable;
import model.map.MapGenerator;
import model.map.building.Building;
import model.unit.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GameState {
    private final GameMap gameMap;
    private final List<Unit> units;
    private final List<Building> buildings;
    private final List<Destructible> destructibles;
    private final List<Maintainable> maintainables;

    public GameState() {
        this.gameMap = MapGenerator.generateMap();
        units = new ArrayList<>();
        buildings = new ArrayList<>();
        destructibles = new ArrayList<>();
        maintainables = new ArrayList<>();
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public List<Unit> getUnits() {
        return Collections.unmodifiableList(units);
    }

    public List<Building> getBuildings() {
        return Collections.unmodifiableList(buildings);
    }

    public List<Destructible> getDestructibles() {
        return destructibles;
    }

    public List<Maintainable> getMaintainables() {
        return maintainables;
    }
}
