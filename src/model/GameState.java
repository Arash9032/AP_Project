package model;

import model.map.*;
import model.map.building.Building;
import model.map.building.BuildingType;
import model.map.building.production.ProductionBuilding;
import model.map.building.townhall.TownHall;
import model.map.edge.Wall;
import model.map.hex.Hex;
import model.map.hex.Point;
import model.unit.Unit;
import model.unit.UnitType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class GameState {
    private GameMap gameMap;
    private final List<Unit> units;
    private final List<Building> buildings;
    private final List<ProductionBuilding> productionBuildings;
    private final List<Destructible> destructibles;
    private final List<Maintainable> maintainables;
    private TownHall townHall;
    private Season season;

    private Point selectedHexPoint;
    private Unit selectedUnit;

    public GameState() {
        units = new ArrayList<>();
        buildings = new ArrayList<>();
        productionBuildings = new ArrayList<>();
        destructibles = new ArrayList<>();
        maintainables = new ArrayList<>();
        season = Season.SPRING;
    }

    public GameState(GameMap gameMap){
        this();
        this.gameMap = gameMap;
    }

    public static GameState generateNewGameState(){
        GameState newGameState = new GameState(MapGenerator.generateMap());
        newGameState.initGameState();
        return newGameState;
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

    public List<ProductionBuilding> getProductionBuildings() {
        return Collections.unmodifiableList(productionBuildings);
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
        if(building instanceof TownHall) townHall = (TownHall) building;
        if(building instanceof ProductionBuilding) productionBuildings.add((ProductionBuilding) building);
    }

    public void addWall(Wall wall) {
        if(wall == null) return;
        maintainables.add(wall);
        destructibles.add(wall);
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
        if(building instanceof TownHall) townHall = null;
        if(building instanceof ProductionBuilding) productionBuildings.remove((ProductionBuilding) building);
    }

    public void removeWall(Wall wall) {
        if(wall == null) return;
        maintainables.remove(wall);
        destructibles.remove(wall);
    }

    public void cleanupDestroyedEntities() {
        units.removeIf(Destructible::isDestroyed);
        buildings.removeIf(Destructible::isDestroyed);
        maintainables.removeIf(Destructible::isDestroyed);
        destructibles.removeIf(Destructible::isDestroyed);
        productionBuildings.removeIf(Destructible::isDestroyed);

        if (townHall != null && townHall.isDestroyed()) {
            townHall = null;
        }
    }

    public Point getSelectedHexPoint() {
        return selectedHexPoint;
    }

    public void setSelectedHexPoint(Point selectedHexPoint) {
        this.selectedHexPoint = selectedHexPoint;
    }

    public TownHall getTownHall() {
        if(townHall == null) throw new IllegalStateException("TownHall doesn't exist.");
        return townHall;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public void changeSeason(){
        season = season.getNextSeason();
    }

    private void initGameState(){
        addUnit(UnitType.BUILDER.createUnit(gameMap.getHex(0 , 0)));
        addUnit(UnitType.BUILDER.createUnit(gameMap.getHex(0 , 0)));
        addUnit(UnitType.WORKER.createUnit(gameMap.getHex(0 , 0)));
        addUnit(UnitType.WORKER.createUnit(gameMap.getHex(0 , 0)));
        addUnit(UnitType.EXPLORER.createUnit(gameMap.getHex(0 , 0)));
        addBuilding(BuildingType.TOWN_HALL.createBuilding(gameMap.getHex(0 , 0)));
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public List<Unit> getUnitsInHex(Hex targetHex){
        return Collections.unmodifiableList(units.stream()
                .filter(unit -> targetHex.getCoordinate().equals(unit.getCurrentHex().getCoordinate()))
                .collect(Collectors.toList()));
    }
}