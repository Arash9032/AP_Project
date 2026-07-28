package model.command;

import config.Constants;
import model.map.building.townhall.TownHall;

public class UpgradeTownHallCommand extends ProductionCommand{

    public UpgradeTownHallCommand(TownHall townHall, int turnCost) {
        super(townHall, turnCost);
    }

    @Override
    public boolean execute() {
        if(!isDone()) throw new IllegalStateException("UpgradeTownHallCommand is not done yet. executed too soon.");
        super.getTownHall().upgrade();
        super.getTownHall().setActiveTask(null);
        return false;
    }
}
