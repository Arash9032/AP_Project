package controller.logic;

import controller.eventbus.EventBus;
import controller.eventbus.TurnEndedEvent;
import model.GameState;
import model.map.building.Building;
import model.map.building.townhall.TownHall;

public class TurnBasedProductionController {
    private GameState gameState;

    public TurnBasedProductionController(GameState gameState) {
        this.gameState = gameState;
        EventBus.getInstance().subscribe(TurnEndedEvent.class , event -> advanceProductions());
    }
    private void advanceProductions(){
        TownHall townHall = gameState.getTownHall();
        if(townHall == null) throw new IllegalStateException("Town hall doesn't exist.");
        if(townHall.getActiveTask() != null) townHall.getActiveTask().execute();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
