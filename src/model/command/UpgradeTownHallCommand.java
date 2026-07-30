package model.command;

import model.map.building.townhall.TownHall;

public class UpgradeTownHallCommand extends ProductionCommand{

    public UpgradeTownHallCommand(TownHall townHall, int turnCost) {
        super(townHall, turnCost);
    }

    @Override
    public void execute() {
        decrementTurn();

        if(isDone()) {
            getTownHall().completeUpgrade();
            getTownHall().setActiveTask(null);
        }
    }
}
