package controller.logic;

import controller.eventbus.EventBus;
import controller.eventbus.TurnEndedEvent;
import model.GameState;
import model.unit.Unit;

public class TurnManager {
    private GameState gameState;
    private int currentTurn;

    public TurnManager(GameState gameState) {
        this.gameState = gameState;
        this.currentTurn = 1;
    }

    public void endTurn() {
        currentTurn++;
        resetUnitsAP();
        EventBus.getInstance().publish(new TurnEndedEvent(currentTurn));
    }

    private void resetUnitsAP() {
        for (Unit unit : gameState.getUnits()) {
            unit.resetAP();
        }
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}