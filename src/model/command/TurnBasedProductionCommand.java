package model.command;

import model.map.building.townhall.TownHall;

public abstract class TurnBasedProductionCommand implements Command{

    private final TownHall townHall;

    private int remainingTurns;

    public TurnBasedProductionCommand(TownHall townHall , int turnCost){
        this.townHall = townHall;
        this.remainingTurns = turnCost;
    }
    public void cancel(){
        townHall.setActiveTask(null);
    }

    public int getRemainingTurns(){
        return remainingTurns;
    }
    public void decrementTurn() {
        remainingTurns--;
    }

    public boolean isDone(){
        return remainingTurns <= 0;
    }

    public TownHall getTownHall() {
        return townHall;
    }

}
