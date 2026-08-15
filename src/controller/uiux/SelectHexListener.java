package controller.uiux;

import model.GameState;
import model.map.hex.Point;
import view.panel.GamePanel;
import view.panel.SideMenuPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectHexListener extends MouseAdapter {
    private GameState gameState;
    private GamePanel gamePanel;
    private SideMenuPanel sideMenuPanel;

    public SelectHexListener(GameState gameState, GamePanel gamePanel, SideMenuPanel sideMenuPanel) {
        this.gameState = gameState;
        this.gamePanel = gamePanel;
        this.sideMenuPanel = sideMenuPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point selectedHexPoint = gamePanel.getSelectedHexPoint(e.getX() , e.getY());
        gameState.setSelectedHexPoint(selectedHexPoint);
        sideMenuPanel.updateSelectedHex(gameState.getGameMap().getHex(selectedHexPoint), gameState.getUnitsInHex(gameState.getGameMap().getHex(selectedHexPoint)));
    }
}
