package view.render.hex;

import config.Constants;
import model.GameState;
import model.map.edge.EdgeKey;
import model.map.edge.HexEdge;
import model.map.hex.Hex;
import model.map.hex.Point;
import model.map.hex.TerrainType;
import util.HexMath;
import view.camera.Camera;
import view.render.AbstractRenderer;
import view.render.RenderContext;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.EnumMap;
import java.util.Map;

public class HexRenderer extends AbstractRenderer {

    private static final double HEX_DRAW_RATIO = 0.92;

    private final Path2D.Double baseHex = new Path2D.Double();
    private final Line2D.Double edgeLine = new Line2D.Double();

    private final BasicStroke hexStroke = new BasicStroke(1.5f);
    private final BasicStroke selectedHexStroke = new BasicStroke(4.0f);
    private final BasicStroke riverStroke = new BasicStroke(8.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

    private final double[] sin = new double[6];
    private final double[] cos = new double[6];
    private final Map<TerrainType, Color> terrainColors;

    public HexRenderer(GameState gameState, Camera camera, RenderContext renderContext) {
        super(gameState, camera, renderContext);
        this.terrainColors = new EnumMap<>(TerrainType.class);
        initializeColors();
        initializeSinCos();
        adjustBaseHex(Constants.BASE_HEX_SIZE * HEX_DRAW_RATIO);
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

        double hexSize = getCamera().getHexSize();
        double zoom = hexSize / Constants.BASE_HEX_SIZE;
        double centerX = getCamera().getCenterX(screenWidth);
        double centerY = getCamera().getCenterY(screenHeight);

        renderHexes(g2, screenWidth, screenHeight, centerX, centerY, zoom, hexSize);
        renderEdges(g2, screenWidth, screenHeight, centerX, centerY, zoom, hexSize);
        renderHighlights(g2, screenWidth, screenHeight, centerX, centerY, zoom, hexSize);
    }

    private void renderHexes(Graphics2D g2, int screenWidth, int screenHeight, double centerX, double centerY, double zoom, double hexSize) {
        for (Hex hex : getGameState().getGameMap().getHexes().values()) {
            double worldX = getWorldX(hex.getCoordinate().getQ(), hex.getCoordinate().getR());
            double worldY = getWorldY(hex.getCoordinate().getR());

            if (!HexMath.isHexInSight(centerX + worldX * zoom, centerY + worldY * zoom, hexSize, screenWidth, screenHeight)) {
                continue;
            }

            drawHex(g2, hex, worldX, worldY);
        }
    }

    private void renderEdges(Graphics2D g2, int screenWidth, int screenHeight, double centerX, double centerY, double zoom, double hexSize) {
        for (Map.Entry<EdgeKey, HexEdge> entry : getGameState().getGameMap().getEdges().entrySet()) {
            HexEdge edge = entry.getValue();

            if (!edge.hasRiver()) {
                continue;
            }

            EdgeKey key = entry.getKey();

            double worldX1 = getWorldX(key.getP1().getQ(), key.getP1().getR());
            double worldY1 = getWorldY(key.getP1().getR());

            double worldX2 = getWorldX(key.getP2().getQ(), key.getP2().getR());
            double worldY2 = getWorldY(key.getP2().getR());

            double screenX1 = centerX + worldX1 * zoom;
            double screenY1 = centerY + worldY1 * zoom;
            double screenX2 = centerX + worldX2 * zoom;
            double screenY2 = centerY + worldY2 * zoom;

            if (!HexMath.isHexInSight(screenX1, screenY1, hexSize, screenWidth, screenHeight) &&
                    !HexMath.isHexInSight(screenX2, screenY2, hexSize, screenWidth, screenHeight)) {
                continue;
            }

            drawEdge(g2, edge, worldX1, worldY1, worldX2, worldY2);
        }
    }

    private void renderHighlights(Graphics2D g2, int screenWidth, int screenHeight, double centerX, double centerY, double zoom, double hexSize) {
        if(getRenderContext().isResourceOverlayActive()) return;
        Point selectedPoint = getGameState().getSelectedHexPoint();
        if (selectedPoint != null) {
            double worldX = getWorldX(selectedPoint.getQ(), selectedPoint.getR());
            double worldY = getWorldY(selectedPoint.getR());

            if (HexMath.isHexInSight(centerX + worldX * zoom, centerY + worldY * zoom, hexSize, screenWidth, screenHeight)) {
                drawSelectionHighlight(g2, worldX, worldY);
            }
        }
    }

    private void drawHex(Graphics2D g2, Hex hex, double worldX, double worldY) {
        g2.translate(worldX, worldY);

        g2.setColor(terrainColors.getOrDefault(hex.getTerrain(), Color.WHITE));
        g2.fill(baseHex);

        g2.translate(-worldX, -worldY);
    }

    private void drawEdge(Graphics2D g2, HexEdge edge, double worldX1, double worldY1, double worldX2, double worldY2) {
        double mx = (worldX1 + worldX2) / 2.0;
        double my = (worldY1 + worldY2) / 2.0;

        double dx = worldX2 - worldX1;
        double dy = worldY2 - worldY1;

        double length = Math.sqrt(dx * dx + dy * dy);
        if (length == 0) return;

        double ux = -dy / length;
        double uy = dx / length;

        double halfEdgeLength = Constants.BASE_HEX_SIZE / 2.0;

        double x1 = mx + ux * halfEdgeLength;
        double y1 = my + uy * halfEdgeLength;
        double x2 = mx - ux * halfEdgeLength;
        double y2 = my - uy * halfEdgeLength;

        edgeLine.setLine(x1, y1, x2, y2);

        Stroke originalStroke = g2.getStroke();

        if (edge.hasRiver()) {
            g2.setColor(Constants.RIVER_COLOR);
            g2.setStroke(riverStroke);
            g2.draw(edgeLine);
        }

        g2.setStroke(originalStroke);
    }

    private void drawSelectionHighlight(Graphics2D g2, double worldX, double worldY) {
        g2.translate(worldX, worldY);
        Stroke originalStroke = g2.getStroke();

        g2.setColor(Color.YELLOW);
        g2.setStroke(selectedHexStroke);
        g2.draw(baseHex);

        g2.setStroke(originalStroke);
        g2.translate(-worldX, -worldY);
    }

    private void adjustBaseHex(double drawSize) {
        baseHex.reset();
        for (int i = 0; i < 6; i++) {
            double x = drawSize * cos[i];
            double y = drawSize * sin[i];
            if (i == 0) baseHex.moveTo(x, y);
            else baseHex.lineTo(x, y);
        }
        baseHex.closePath();
    }

    private double getWorldX(int q, int r) {
        return Constants.BASE_HEX_SIZE * HexMath.SQRT_3 * (q + r / 2.0);
    }

    private double getWorldY(int r) {
        return Constants.BASE_HEX_SIZE * 3.0 / 2.0 * r;
    }
}