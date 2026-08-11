package view.render.building;

import model.GameState;
import model.map.building.Building;
import model.map.hex.Hex;
import util.HexMath;
import view.camera.Camera;

import java.awt.*;

public class BuildingRenderer {

    private GameState gameState;
    private Camera camera;

    public BuildingRenderer(GameState gameState, Camera camera) {
        this.gameState = gameState;
        this.camera = camera;
    }

    public void render(Graphics2D g2, int screenWidth, int screenHeight) {
        if (gameState == null || camera == null) return;

        double hexSize = camera.getHexSize();
        double centerX = camera.getCenterX(screenWidth);
        double centerY = camera.getCenterY(screenHeight);

        double bSize = hexSize * 0.5;

        for (Building building : gameState.getBuildings()) {
            Hex hex = building.getLocation();
            if (hex == null) continue;

            int q = hex.getCoordinate().getQ();
            int r = hex.getCoordinate().getR();

            double cx = centerX + hexSize * HexMath.SQRT_3 * (q + r / 2.0);
            double cy = centerY + hexSize * 3.0 / 2.0 * r;

            g2.translate(cx, cy);

            Graphics2D g2d = (Graphics2D) g2.create();
            BuildingTypeRenderer.getFromType(building.getType()).drawBuilding(g2 , bSize, building.getHp(), building.getMaximumHp());
            g2d.dispose();

            g2.translate(-cx, -cy);
        }
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