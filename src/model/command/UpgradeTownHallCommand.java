package model.command;

import model.map.building.townhall.TownHall;

public class UpgradeTownHallCommand extends ProductionCommand{

    public UpgradeTownHallCommand(TownHall townHall, int turnCost) {
        super(townHall, turnCost);
    }

    @Override
    public void execute() {
        if(!isDone()) throw new IllegalStateException("UpgradeTownHallCommand is not done yet. executed too soon.");
        getTownHall().upgrade();
        getTownHall().setActiveTask(null);
    }
}
