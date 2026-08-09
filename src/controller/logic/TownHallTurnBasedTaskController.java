package controller.logic;

import controller.eventbus.TurnEndedEvent;
import model.GameState;
import model.map.building.townhall.TownHall;

public class TownHallTurnBasedTaskController extends EventListenerController {

    public TownHallTurnBasedTaskController(GameState gameState) {
        super(gameState);
        subscribeEvent(TurnEndedEvent.class , event -> advanceTask());
    }
    private void advanceTask(){
        TownHall townHall = getGameState().getTownHall();
        if(townHall.getActiveTask() != null) townHall.getActiveTask().execute();
    }
}
