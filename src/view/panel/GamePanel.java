package view.panel;

import config.Constants;
import model.GameState;
import model.map.hex.Hex;
import model.map.hex.TerrainType;
import view.MainContentPane;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.EnumMap;
import java.util.Map;

public class GamePanel extends JPanel {
    private static final double MAX_HEX_SIZE = 64;
    private static final double MIN_HEX_SIZE = 16;

    private final MainContentPane mainContentPane;
    private GameState gameState;

    private double hexSize = 16;
    private double cameraX = 0;
    private double cameraY = 0;

    private final Map<TerrainType, Color> terrainColors;

    public GamePanel(MainContentPane mainContentPane, GameState gameState) {
        setBackground(Constants.GAME_BACKGROUND_COLOR);
        this.mainContentPane = mainContentPane;
        this.gameState = gameState;
        this.terrainColors = new EnumMap<>(TerrainType.class);
        initializeColors();
    }

    private void initializeColors() {
        terrainColors.put(TerrainType.FOREST, Constants.FOREST_HEX_COLOR);
        terrainColors.put(TerrainType.PLAIN, Constants.PLAIN_HEX_COLOR);
        terrainColors.put(TerrainType.MOUNTAIN, Constants.MOUNTAIN_HEX_COLOR);
        terrainColors.put(TerrainType.MEADOW, Constants.MEADOW_HEX_COLOR);
        terrainColors.put(TerrainType.MOUNTAIN_RANGE, Constants.MOUNTAIN_RANGE_HEX_COLOR);
        terrainColors.put(TerrainType.SEA, Constants.SEA_HEX_COLOR);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameState == null || gameState.getGameMap() == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL , RenderingHints.VALUE_STROKE_PURE);

        double centerX = getWidth() / 2.0 + cameraX;
        double centerY = getHeight() / 2.0 + cameraY;

        for (Hex hex : gameState.getGameMap().getHexes().values()) {
            int q = hex.getCoordinate().getQ();
            int r = hex.getCoordinate().getR();

            double cx = (centerX + hexSize * Math.sqrt(3) * (q + r / 2.0));
            double cy = (centerY + hexSize * 3.0 / 2.0 * r);

            Path2D.Double hexPolygon = createHexagon(cx, cy, hexSize);

            g2.setColor(terrainColors.getOrDefault(hex.getTerrain(), Color.WHITE));
            g2.fill(hexPolygon);

            g2.setColor(Constants.HEX_BORDER_COLORS);
            g2.setStroke(new BasicStroke(1.5f));
            g2.draw(hexPolygon);
        }

        g2.dispose();
    }

    private Path2D.Double createHexagon(double cx, double cy, double size) {
        Path2D.Double polygon = new Path2D.Double();
        for (int i = 0; i < 6; i++) {
            double angleDeg = 60 * i - 30;
            double angleRad = Math.toRadians(angleDeg);
            double x = (cx + size * Math.cos(angleRad));
            double y = (cy + size * Math.sin(angleRad));
            if (i==0) polygon.moveTo(x , y);
            else polygon.lineTo(x , y);
        }
        polygon.closePath();
        return polygon;
    }

    public void applyZoom(double delta){
        hexSize += delta;
        if (hexSize > MAX_HEX_SIZE) hexSize = MAX_HEX_SIZE;
        else if (hexSize < MIN_HEX_SIZE) hexSize = MIN_HEX_SIZE;
    }
}