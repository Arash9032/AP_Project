package model.command;

import model.map.building.townhall.TownHall;
import model.map.hex.Hex;
import model.unit.Unit;
import model.unit.UnitType;

public class ProduceUnitCommand extends TurnBasedProductionCommand {

    private final UnitType unitType;
    private final Hex location;

    public ProduceUnitCommand(TownHall townHall, UnitType unitType, Hex location) {
        super(townHall, unitType.getProductionTurnCost());

        if (!townHall.getInventory().hasEnoughResources(unitType.getProductionCost())) {
            throw new IllegalStateException("Not enough resources to produce this unit.");
        }

        townHall.getInventory().consumeResources(unitType.getProductionCost());

        this.unitType = unitType;
        this.location = location;
    }

    @Override
    public void execute() {
        decrementTurn();

        if (isDone()) {
            Unit newUnit = unitType.createUnit(location);
            getTownHall().setActiveTask(null);
        }
    }
}