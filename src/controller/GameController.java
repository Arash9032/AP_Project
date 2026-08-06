package controller;

import model.GameState;
import view.panel.GamePanel;

public final class GameController {
    private GameState gameState;
    private GamePanel gamePanel;

    public GameController(GameState gameState, GamePanel gamePanel) {
        this.gameState = gameState;
        this.gamePanel = gamePanel;
        registerListeners();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    private void registerListeners(){
        ZoomListener zoomListener = new ZoomListener(gamePanel);
        gamePanel.addMouseWheelListener(zoomListener);

        CameraDragListener dragListener = new CameraDragListener(gamePanel);
        gamePanel.addMouseListener(dragListener);
        gamePanel.addMouseMotionListener(dragListener);
    }

}
