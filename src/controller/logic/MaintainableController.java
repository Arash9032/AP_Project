package controller.logic;

import controller.eventbus.EventBus;
import controller.eventbus.TurnEndedEvent;
import model.GameState;
import model.map.Maintainable;
import model.map.building.townhall.TownHall;

public class MaintainableController {
    private GameState gameState;

    public MaintainableController(GameState gameState) {
        this.gameState = gameState;
        EventBus.getInstance().subscribe(TurnEndedEvent.class , event -> onEndTurn());
    }

    private void onEndTurn(){
        processUpkeepCost();
    }

    private void processUpkeepCost(){
        TownHall townHall = gameState.getTownHall();
        for(Maintainable maintainable : gameState.getMaintainables()){
            maintainable.checkUpkeepPayment(townHall.getInventory().consumeResources(maintainable.getUpkeepCost()));
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
