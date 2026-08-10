package view.panel;

import config.Constants;
import model.GameState;
import model.map.hex.Point;
import util.HexMath;
import view.MainContentPane;
import view.camera.Camera;
import view.render.HexRenderer;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final MainContentPane mainContentPane;
    private GameState gameState;

    private final Camera camera;
    private final HexRenderer hexRenderer;

    public GamePanel(MainContentPane mainContentPane, GameState gameState) {
        setBackground(Constants.GAME_BACKGROUND_COLOR);
        this.mainContentPane = mainContentPane;
        this.gameState = gameState;

        this.camera = new Camera(Camera.MIN_HEX_SIZE);
        this.hexRenderer = new HexRenderer();
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

        hexRenderer.render(g2, gameState, camera, getWidth(), getHeight());

        g2.dispose();
    }
}