package model.command;

import model.map.building.townhall.TownHall;
import model.map.hex.Hex;
import model.unit.Unit;
import model.unit.UnitType;

public class ProduceUnitCommand extends ProductionCommand{

    private final UnitType unitType;

    private final Hex location;

    public ProduceUnitCommand(TownHall townHall, UnitType unitType, Hex location) {
        super(townHall, unitType.getTurnCost());
        this.unitType = unitType;
        this.location = location;
    }

    @Override
    public void execute() {
        if(!isDone()) throw new IllegalStateException("ProduceUnitCommand is not done yet. executed too soon.");
        Unit newUnit = unitType.createUnit(location);
        // TODO:
        super.getTownHall().setActiveTask(null);
    }
}
