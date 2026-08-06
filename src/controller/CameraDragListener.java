package controller;

import view.panel.GamePanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CameraDragListener extends MouseAdapter {
    private GamePanel gamePanel;
    private Point lastMousePosition;

    public CameraDragListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastMousePosition = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (lastMousePosition == null) return;
        double dx = e.getX() - lastMousePosition.x;
        double dy = e.getY() - lastMousePosition.y;

        gamePanel.setCameraX(gamePanel.getCameraX() + dx);
        gamePanel.setCameraY(gamePanel.getCameraY() + dy);
        lastMousePosition = e.getPoint();
    }
}
