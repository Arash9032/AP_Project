package controller;

import config.Constants;
import model.GameState;
import model.map.MapGenerator;
import view.MainFrame;

import javax.swing.*;

public final class GameEngine {
    private GameState gameState;
    private MainFrame mainFrame;
    private GameController controller;
    private Timer timer;

    public GameEngine(){
        mainFrame = new MainFrame(this);
        timer = new Timer(1000/ Constants.FRAME_RATE, e ->
        {
            if(mainFrame != null && mainFrame.getMainContentPane() != null && mainFrame.getMainContentPane().getGamePanel() != null)
                mainFrame.getMainContentPane().getGamePanel().repaint();
        });
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
        gameState = new GameState(MapGenerator.generateMap());
        controller = new GameController(gameState , mainFrame.getMainContentPane().getGamePanel());
        timer.start();
    }
}
