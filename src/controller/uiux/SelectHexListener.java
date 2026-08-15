package controller.uiux;

import model.GameState;
import view.panel.GamePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectHexListener extends MouseAdapter {
    private GameState gameState;
    private GamePanel gamePanel;

    public SelectHexListener(GameState gameState, GamePanel gamePanel) {
        this.gameState = gameState;
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gameState.setSelectedHexPoint(gamePanel.getSelectedHexPoint(e.getX() , e.getY()));
    }
}
