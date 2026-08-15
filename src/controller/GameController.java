package controller;

import controller.uiux.CameraDragListener;
import controller.uiux.SelectHexListener;
import controller.uiux.SelectUnitListener;
import controller.uiux.ZoomListener;
import model.GameState;
import view.GameContainer;

public final class GameController {
    private GameState gameState;
    private GameContainer gameContainer;

    public GameController(GameState gameState, GameContainer gameContainer) {
        this.gameState = gameState;
        this.gameContainer = gameContainer;
        registerListeners();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameContainer getGameContainer() {
        return gameContainer;
    }

    public void setGameContainer(GameContainer gameContainer) {
        this.gameContainer = gameContainer;
    }

    private void registerListeners(){
        ZoomListener zoomListener = new ZoomListener(gameContainer.getGamePanel());
        gameContainer.getGamePanel().addMouseWheelListener(zoomListener);

        CameraDragListener dragListener = new CameraDragListener(gameContainer.getGamePanel());
        gameContainer.getGamePanel().addMouseListener(dragListener);
        gameContainer.getGamePanel().addMouseMotionListener(dragListener);

        SelectHexListener selectHexListener = new SelectHexListener(gameState , gameContainer.getGamePanel(), gameContainer.getSideMenuPanel());
        gameContainer.getGamePanel().addMouseListener(selectHexListener);

        SelectUnitListener selectUnitListener = new SelectUnitListener(gameState);
        gameContainer.getSideMenuPanel().getUnitList().addListSelectionListener(selectUnitListener);
    }

}
