package view.panel;

import config.Constants;
import model.GameState;
import model.map.hex.Point;
import util.HexMath;
import view.camera.Camera;
import view.render.WorldRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    private GameState gameState;

    private final Camera camera;
    private final WorldRenderer worldRenderer;

    public GamePanel(GameState gameState) {
        setBackground(Constants.GAME_BACKGROUND_COLOR);
        this.gameState = gameState;

        this.camera = new Camera(Camera.MIN_HEX_SIZE);
        worldRenderer = new WorldRenderer(gameState, camera);
        setFocusable(true);
        initFocusListener();
        setFocusTraversalKeysEnabled(false);
    }

    private void initFocusListener(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }
    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        worldRenderer.setGameState(gameState);
        repaint();
        revalidate();
    }

    public Camera getCamera() {
        return camera;
    }

    public Point getSelectedHexPoint(double mouseX, double mouseY) {
        Point selectedPoint = HexMath.pixelToHex(mouseX, mouseY,
                camera.getCenterX(getWidth()),
                camera.getCenterY(getHeight()),
                camera.getHexSize());
        if (gameState.getGameMap().getHex(selectedPoint) == null) return null;
        return selectedPoint;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        worldRenderer.renderWorld(g2, getWidth(), getHeight());

        g2.dispose();
    }

    public WorldRenderer getWorldRenderer() {
        return worldRenderer;
    }
}