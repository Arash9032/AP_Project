package view.panel;

import config.Constants;
import model.GameState;
import model.map.hex.Hex;
import model.map.hex.TerrainType;
import view.MainContentPane;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class GamePanel extends JPanel {
    private final MainContentPane mainContentPane;
    private GameState gameState;

    private int hexSize = 16;
    private int cameraX = 0;
    private int cameraY = 0;

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

        int centerX = getWidth() / 2 + cameraX;
        int centerY = getHeight() / 2 + cameraY;

        for (Hex hex : gameState.getGameMap().getHexes().values()) {
            int q = hex.getCoordinate().getQ();
            int r = hex.getCoordinate().getR();

            int cx = (int) (centerX + hexSize * Math.sqrt(3) * (q + r / 2.0));
            int cy = (int) (centerY + hexSize * 3.0 / 2.0 * r);

            Polygon hexPolygon = createHexagon(cx, cy, hexSize);

            g2.setColor(terrainColors.getOrDefault(hex.getTerrain(), Color.WHITE));
            g2.fill(hexPolygon);

            g2.setColor(Constants.HEX_BORDER_COLORS);
            g2.setStroke(new BasicStroke(1.5f));
            g2.draw(hexPolygon);
        }

        g2.dispose();
    }

    private Polygon createHexagon(int cx, int cy, int size) {
        Polygon polygon = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angleDeg = 60 * i - 30;
            double angleRad = Math.PI / 180 * angleDeg;
            int x = (int) (cx + size * Math.cos(angleRad));
            int y = (int) (cy + size * Math.sin(angleRad));
            polygon.addPoint(x, y);
        }
        return polygon;
    }
}