package controller.logic;

import controller.eventbus.TurnEndedEvent;
import model.GameState;
import model.unit.Unit;

public class UnitController extends EventListenerController {

    public UnitController(GameState gameState){
        super(gameState);
        subscribeEvent(TurnEndedEvent.class , event -> onEndTurn());
    }

    private void onEndTurn(){
        resetUnitsAP();
    }

    private void resetUnitsAP() {
        for (Unit unit : getGameState().getUnits()) {
            unit.resetAP();
        }
    }
}
