package controller.logic;

import config.Constants;
import controller.eventbus.TurnEndedEvent;
import model.GameState;

public class SeasonController extends EventListenerController {

    public SeasonController(GameState gameState) {
        super(gameState);
        subscribeEvent(TurnEndedEvent.class , event -> onEndTurn(event.getNewTurn()));
    }

    private void onEndTurn(int newTurn){
        checkSeasonChange(newTurn);
    }

    private void checkSeasonChange(int newTurn){
        if(newTurn > 1 && newTurn % Constants.SEASON_DURATION_IN_TURN == 1) getGameState().changeSeason();
    }
}
