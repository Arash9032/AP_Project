package controller;

import model.GameState;
import view.MainFrame;

public final class GameEngine {
    private GameState gameState;
    private MainFrame mainFrame;
    public GameEngine(){
        gameState = new GameState();
        mainFrame = new MainFrame(this);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void startNewGame(){
        gameState = new GameState();
    }
}
