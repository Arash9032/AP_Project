package controller;

import view.panel.GamePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

public class ZoomListener extends MouseAdapter {
    private GamePanel gamePanel;

    public ZoomListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        gamePanel.getCamera().applyZoom(e.getPreciseWheelRotation(), e.getX(), e.getY(), gamePanel.getWidth(), gamePanel.getHeight());
    }
}
