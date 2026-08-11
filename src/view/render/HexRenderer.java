package view.render;

import config.Constants;
import model.GameState;
import model.map.hex.Hex;
import model.map.hex.Point;
import model.map.hex.TerrainType;
import util.HexMath;
import view.camera.Camera;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.EnumMap;
import java.util.Map;

public class HexRenderer {
    private GameState gameState;
    private Camera camera;

    private final Path2D.Double baseHex = new Path2D.Double();
    private final BasicStroke hexStroke = new BasicStroke(1.5f);
    private final BasicStroke selectedHexStroke = new BasicStroke(3.0f);

    private final double[] sin = new double[6];
    private final double[] cos = new double[6];
    private final Map<TerrainType, Color> terrainColors;

    public HexRenderer(GameState gameState, Camera camera) {
        this.gameState = gameState;
        this.camera = camera;
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

    public void render(Graphics2D g2, int screenWidth, int screenHeight) {
        if (gameState == null || gameState.getGameMap() == null) return;
        renderHexes(g2, screenWidth, screenHeight);
    }

    private void renderHexes(Graphics2D g2, int screenWidth, int screenHeight){
        double hexSize = camera.getHexSize();
        double centerX = camera.getCenterX(screenWidth);
        double centerY = camera.getCenterY(screenHeight);

        adjustBaseHex(hexSize);

        for (Hex hex : gameState.getGameMap().getHexes().values()) {
            drawHex(g2, hex, centerX, centerY, hexSize);
        }

        Point selectedPoint = gameState.getSelectedHexPoint();
        if (selectedPoint != null) {
            drawSelectionHighlight(g2, selectedPoint, centerX, centerY, hexSize);
        }
    }

    private void drawHex(Graphics2D g2, Hex hex, double centerX, double centerY, double hexSize) {
        int q = hex.getCoordinate().getQ();
        int r = hex.getCoordinate().getR();

        double cx = centerX + hexSize * HexMath.SQRT_3 * (q + r / 2.0);
        double cy = centerY + hexSize * 3.0 / 2.0 * r;

        g2.translate(cx, cy);

        g2.setColor(terrainColors.getOrDefault(hex.getTerrain(), Color.WHITE));
        g2.fill(baseHex);

        g2.setColor(Constants.HEX_BORDER_COLORS);
        g2.setStroke(hexStroke);
        g2.draw(baseHex);

        g2.translate(-cx, -cy);
    }

    private void drawSelectionHighlight(Graphics2D g2, Point selectedPoint, double centerX, double centerY, double hexSize) {
        int q = selectedPoint.getQ();
        int r = selectedPoint.getR();

        double cx = centerX + hexSize * HexMath.SQRT_3 * (q + r / 2.0);
        double cy = centerY + hexSize * 3.0 / 2.0 * r;

        g2.translate(cx, cy);

        g2.setColor(Color.YELLOW);
        g2.setStroke(selectedHexStroke);
        g2.draw(baseHex);

        g2.translate(-cx, -cy);
    }

    private void adjustBaseHex(double hexSize) {
        baseHex.reset();
        for (int i = 0; i < 6; i++) {
            double x = hexSize * cos[i];
            double y = hexSize * sin[i];
            if (i == 0) baseHex.moveTo(x, y);
            else baseHex.lineTo(x, y);
        }
        baseHex.closePath();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}