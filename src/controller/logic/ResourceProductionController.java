package controller.logic;

import controller.eventbus.EventBus;
import controller.eventbus.TurnEndedEvent;
import model.GameState;
import model.map.building.production.ProductionBuilding;

public class ResourceProductionController {
    private GameState gameState;

    public ResourceProductionController(GameState gameState) {
        this.gameState = gameState;
        EventBus.getInstance().subscribe(TurnEndedEvent.class , event -> onEndTurn());
    }

    private void onEndTurn(){
        produceResources();
    }

    private void produceResources(){
        for(ProductionBuilding productionBuilding : gameState.getProductionBuildings()){
            gameState.getTownHall().getInventory().addResource(productionBuilding.getProducedResource() , productionBuilding.calculateProduction());
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
