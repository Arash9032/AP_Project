package controller.logic;

import controller.eventbus.EventBus;
import controller.eventbus.TurnEndedEvent;
import model.GameState;
import model.unit.Unit;

public class UnitController {
    private GameState gameState;

    public UnitController(GameState gameState){
        this.gameState = gameState;
        EventBus.getInstance().subscribe(TurnEndedEvent.class , event -> onEndTurn());
    }

    private void onEndTurn(){
        resetUnitsAP();
    }

    private void resetUnitsAP() {
        for (Unit unit : gameState.getUnits()) {
            unit.resetAP();
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
