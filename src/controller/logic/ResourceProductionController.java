package controller.logic;

import controller.eventbus.TurnEndedEvent;
import model.GameState;
import model.map.building.production.ProductionBuilding;

public class ResourceProductionController extends EventListenerController {
    public ResourceProductionController(GameState gameState) {
        super(gameState);
        subscribeEvent(TurnEndedEvent.class , event -> onEndTurn());
    }

    private void onEndTurn(){
        produceResources();
    }

    private void produceResources(){
        for(ProductionBuilding productionBuilding : getGameState().getProductionBuildings()){
            getGameState().getTownHall().getInventory().addResource(productionBuilding.getProducedResource() , productionBuilding.calculateProduction());
        }
    }
}
