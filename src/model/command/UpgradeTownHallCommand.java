package model.command;

import config.Constants;
import model.map.building.townhall.TownHall;

public class UpgradeTownHallCommand implements ProductionCommand{

    private final TownHall townHall;

    private int remainingTurns;

    public UpgradeTownHallCommand(TownHall townHall , int turnCost){
        this.townHall = townHall;
        this.remainingTurns = turnCost;
    }

    @Override
    public void cancel() {
        townHall.setActiveTask(null);
    }

    @Override
    public int getRemainingTurns() {
        return remainingTurns;
    }

    @Override
    public void decrementTurn() {
        remainingTurns--;
    }

    @Override
    public boolean execute() {
        if(!isDone()) throw new IllegalStateException("UpgradeTownHallCommand is not done yet. executed too soon.");
        townHall.upgrade();
        townHall.setActiveTask(null);
        return false;
    }

    public TownHall getTownHall() {
        return townHall;
    }

    @Override
    public boolean isDone() {
        return remainingTurns <= 0;
    }
}
