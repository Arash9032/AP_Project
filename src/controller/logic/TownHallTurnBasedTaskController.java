package controller.logic;

import controller.eventbus.EventBus;
import controller.eventbus.TurnEndedEvent;
import model.GameState;
import model.map.building.townhall.TownHall;

public class TownHallTurnBasedTaskController {
    private GameState gameState;
    public TownHallTurnBasedTaskController(GameState gameState) {
        this.gameState = gameState;
        EventBus.getInstance().subscribe(TurnEndedEvent.class , event -> advanceTask());
    }
    private void advanceTask(){
        TownHall townHall = gameState.getTownHall();
        if(townHall.getActiveTask() != null) townHall.getActiveTask().execute();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
