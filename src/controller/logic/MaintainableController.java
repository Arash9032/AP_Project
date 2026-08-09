package controller.logic;

import controller.eventbus.TurnEndedEvent;
import model.GameState;
import model.map.Maintainable;
import model.map.building.townhall.TownHall;

public class MaintainableController extends EventListenerController {
    public MaintainableController(GameState gameState) {
        super(gameState);
        subscribeEvent(TurnEndedEvent.class , event -> onEndTurn());
    }

    private void onEndTurn(){
        processUpkeepCost();
    }

    private void processUpkeepCost(){
        TownHall townHall = getGameState().getTownHall();
        for(Maintainable maintainable : getGameState().getMaintainables()){
            maintainable.checkUpkeepPayment(townHall.getInventory().consumeResources(maintainable.getUpkeepCost()));
        }
    }
}
