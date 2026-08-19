package view.render.hex;

import config.Constants;
import model.GameState;
import model.map.hex.Hex;
import model.map.hex.Point;
import model.map.hex.TerrainType;
import util.HexMath;
import view.camera.Camera;
import view.render.AbstractRenderer;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.EnumMap;
import java.util.Map;

public class HexRenderer extends AbstractRenderer {

    private final Path2D.Double baseHex = new Path2D.Double();
    private final BasicStroke hexStroke = new BasicStroke(1.5f);
    private final BasicStroke selectedHexStroke = new BasicStroke(3.0f);

    private final double[] sin = new double[6];
    private final double[] cos = new double[6];
    private final Map<TerrainType, Color> terrainColors;

    public HexRenderer(GameState gameState, Camera camera) {
        super(gameState , camera);
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

    @Override
    public void render(Graphics2D g2, int screenWidth, int screenHeight) {
        if (getGameState() == null || getGameState().getGameMap() == null) return;
        renderHexes(g2, screenWidth, screenHeight);
    }

    private void renderHexes(Graphics2D g2, int screenWidth, int screenHeight){
        double hexSize = getCamera().getHexSize();
        double centerX = getCamera().getCenterX(screenWidth);
        double centerY = getCamera().getCenterY(screenHeight);

        adjustBaseHex(hexSize);

        for (Hex hex : getGameState().getGameMap().getHexes().values()) {
            double cx = getCX(centerX, hexSize, hex.getCoordinate().getQ(), hex.getCoordinate().getR());
            double cy = getCY(centerY, hexSize, hex.getCoordinate().getR());

            if(!isHexInSight(cx, cy, hexSize, screenWidth, screenHeight))
                continue;

            drawHex(g2, hex, cx, cy);
        }

        Point selectedPoint = getGameState().getSelectedHexPoint();
        if (selectedPoint != null) {
            double cx = getCX(centerX, hexSize, selectedPoint.getQ(), selectedPoint.getR());
            double cy = getCY(centerY, hexSize, selectedPoint.getR());

            if (isHexInSight(cx, cy, hexSize, screenWidth, screenHeight)) {
                drawSelectionHighlight(g2, cx, cy);
            }
        }
    }

    private void drawHex(Graphics2D g2, Hex hex, double cx, double cy) {
        g2.translate(cx, cy);

        g2.setColor(terrainColors.getOrDefault(hex.getTerrain(), Color.WHITE));
        g2.fill(baseHex);

        g2.setColor(Constants.HEX_BORDER_COLORS);
        g2.setStroke(hexStroke);
        g2.draw(baseHex);

        g2.translate(-cx, -cy);
    }

    private void drawSelectionHighlight(Graphics2D g2, double cx, double cy) {
        g2.translate(cx, cy);
        Stroke originalStroke = g2.getStroke();

        g2.setColor(Color.YELLOW);
        g2.setStroke(selectedHexStroke);
        g2.draw(baseHex);

        g2.setStroke(originalStroke);
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

    private boolean isHexInSight(double cx, double cy, double hexSize, int screenWidth, int screenHeight){
        return !(cx + hexSize < 0 || cx - hexSize > screenWidth ||
                cy + hexSize < 0 || cy - hexSize > screenHeight);
    }

    private double getCX(double centerX, double hexSize, int q , int r){
        return centerX + hexSize * HexMath.SQRT_3 * (q + r / 2.0);
    }

    private double getCY(double centerY, double hexSize, int r){
        return centerY + hexSize * 3.0 / 2.0 * r;
    }
}