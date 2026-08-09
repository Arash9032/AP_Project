package controller.logic;

import controller.eventbus.EventBus;
import controller.eventbus.TurnEndedEvent;
import model.GameState;
import model.map.building.Building;
import model.map.building.townhall.TownHall;

public class BuildingController {
    private GameState gameState;

    public BuildingController(GameState gameState) {
        this.gameState = gameState;
        EventBus.getInstance().subscribe(TurnEndedEvent.class , event -> onEndTurn());
    }

    private void onEndTurn(){
        processUpkeepCost();
    }

    private void processUpkeepCost(){
        TownHall townHall = gameState.getTownHall();
        for(Building building : gameState.getBuildings()){
            if(townHall.getInventory().hasEnoughResources(building.getUpkeepCost()))
                townHall.getInventory().consumeResources(building.getUpkeepCost());
            else building.setConsecutiveUnpaidUpkeep(building.getConsecutiveUnpaidUpkeep() + 1);
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
