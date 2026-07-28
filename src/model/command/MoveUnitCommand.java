package model.command;

import model.map.hex.Hex;
import model.unit.Unit;

public class MoveUnitCommand implements Command {

    private final Unit unit;
    private final Hex destinationHex;

    public MoveUnitCommand(Unit unit, Hex destinationHex) {
        if (unit == null || destinationHex == null) {
            throw new IllegalArgumentException("Unit and destination cannot be null.");
        }
        this.unit = unit;
        this.destinationHex = destinationHex;
    }

    @Override
    public boolean execute() {
        if(unit.move(destinationHex)) return true;
        else throw new IllegalStateException("Cannot execute move command.");
    }

    public Unit getUnit() {
        return unit;
    }

    public Hex getDestinationHex() {
        return destinationHex;
    }
}