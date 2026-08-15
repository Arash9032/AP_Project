package controller.uiux;

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

        gamePanel.getCamera().setX(gamePanel.getCamera().getX() - dx);
        gamePanel.getCamera().setY(gamePanel.getCamera().getY() - dy);
        lastMousePosition = e.getPoint();
    }
}
