package view.panel;

import model.GameState;
import view.MainContentPane;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final MainContentPane mainContentPane;
    private GameState gameState;

    public GamePanel(MainContentPane mainContentPane, GameState gameState) {
        this.mainContentPane = mainContentPane;
        this.gameState = gameState;
    }

    public MainContentPane getMainContentPane() {
        return mainContentPane;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        repaint();
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        //TODO:
    }
}
