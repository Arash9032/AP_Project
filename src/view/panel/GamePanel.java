package view.panel;

import config.Constants;
import model.GameState;
import model.map.hex.Hex;
import model.map.hex.Point;
import model.map.hex.TerrainType;
import view.MainContentPane;
import view.camera.Camera;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.EnumMap;
import java.util.Map;

public class GamePanel extends JPanel {

    private static final double SQRT_3 = Math.sqrt(3);

    private final Path2D.Double baseHex = new Path2D.Double();
    private final BasicStroke hexStroke = new BasicStroke(1.5f);
    private final double[] sin = new double[6];
    private final double[] cos = new double[6];

    private final MainContentPane mainContentPane;
    private GameState gameState;
    private final Camera camera;

    private final Map<TerrainType, Color> terrainColors;

    public GamePanel(MainContentPane mainContentPane, GameState gameState) {
        setBackground(Constants.GAME_BACKGROUND_COLOR);
        this.mainContentPane = mainContentPane;
        this.gameState = gameState;
        this.camera = new Camera(Camera.MIN_HEX_SIZE);
        this.terrainColors = new EnumMap<>(TerrainType.class);
        initializeColors();
        initializeSinCos();
    }

    private void initializeColors() {
        terrainColors.put(TerrainType.FOREST, Constants.FOREST_HEX_COLOR);
        terrainColors.put(TerrainType.PLAIN, Constants.PLAIN_HEX_COLOR);
        terrainColors.put(TerrainType.MOUNTAIN, Constants.MOUNTAIN_HEX_COLOR);
        terrainColors.put(TerrainType.MEADOW, Constants.MEADOW_HEX_COLOR);
        terrainColors.put(TerrainType.MOUNTAIN_RANGE, Constants.MOUNTAIN_RANGE_HEX_COLOR);
        terrainColors.put(TerrainType.SEA, Constants.SEA_HEX_COLOR);
    }

    private void initializeSinCos() {
        for (int i = 0; i < 6; i++) {
            double angleDeg = 60 * i - 30;
            double angleRad = Math.toRadians(angleDeg);
            sin[i] = Math.sin(angleRad);
            cos[i] = Math.cos(angleRad);
        }
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameState == null || gameState.getGameMap() == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        adjustBaseHex();
        drawHexes(g2);

        g2.dispose();
    }

    private void drawHexes(Graphics2D g2) {
        double hexSize = camera.getHexSize();
        double centerX = camera.getCenterX(getWidth());
        double centerY = camera.getCenterY(getHeight());

        for (Hex hex : gameState.getGameMap().getHexes().values()) {
            int q = hex.getCoordinate().getQ();
            int r = hex.getCoordinate().getR();

            double cx = centerX + hexSize * SQRT_3 * (q + r / 2.0);
            double cy = centerY + hexSize * 3.0 / 2.0 * r;

            g2.translate(cx, cy);
            g2.setColor(terrainColors.getOrDefault(hex.getTerrain(), Color.WHITE));
            g2.fill(baseHex);

            g2.setColor(Constants.HEX_BORDER_COLORS);
            g2.setStroke(hexStroke);
            g2.draw(baseHex);

            g2.translate(-cx, -cy);
        }
    }

    private void adjustBaseHex() {
        baseHex.reset();
        double hexSize = camera.getHexSize();
        for (int i = 0; i < 6; i++) {
            double x = hexSize * cos[i];
            double y = hexSize * sin[i];
            if (i == 0) baseHex.moveTo(x, y);
            else baseHex.lineTo(x, y);
        }
        baseHex.closePath();
    }

    public Point getSelectedHexPoint(double mouseX, double mouseY) {
        double dx = (mouseX - camera.getCenterX(getWidth())) / camera.getHexSize();
        double dy = (mouseY - camera.getCenterY(getHeight())) / camera.getHexSize();

        double qFrac = SQRT_3 / 3.0 * dx - 1.0 / 3.0 * dy;
        double rFrac = 2.0 / 3.0 * dy;

        return axialRound(qFrac, rFrac);
    }

    private Point axialRound(double qFrac, double rFrac) {
        double sFrac = -qFrac - rFrac;
        int q = (int) Math.round(qFrac);
        int r = (int) Math.round(rFrac);
        int s = (int) Math.round(sFrac);

        if (q + r + s == 0) return new Point(q, r);

        double qDiff = Math.abs(qFrac - q);
        double rDiff = Math.abs(rFrac - r);
        double sDiff = Math.abs(sFrac - s);

        if (qDiff >= rDiff) {
            if (qDiff >= sDiff) q = -r - s;
        } else {
            if (rDiff >= sDiff) r = -q - s;
        }
        return new Point(q, r);
    }
}