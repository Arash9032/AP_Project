package model.command;

import model.map.hex.Hex;
import model.unit.Unit;

public class MoveUnitCommand implements UndoableCommand {

    private final Unit unit;
    private final Hex destinationHex;
    private Hex previousHex;

    public MoveUnitCommand(Unit unit, Hex destinationHex) {
        if (unit == null || destinationHex == null) {
            throw new IllegalArgumentException("Unit and destination cannot be null.");
        }
        this.unit = unit;
        this.destinationHex = destinationHex;
    }

    @Override
    public void execute() {
        previousHex = unit.getCurrentHex();
        if(!unit.move(destinationHex))
            throw new IllegalStateException("Cannot execute move command.");
    }

    @Override
    public void undo() {
        if(previousHex == null || !unit.undoMove(previousHex))
            throw new IllegalStateException("Cannot undo move command.");
    }

    public Unit getUnit() {
        return unit;
    }

    public Hex getDestinationHex() {
        return destinationHex;
    }
}