package view.render.building;

import model.GameState;
import model.map.building.Building;
import model.map.hex.Hex;
import util.HexMath;
import view.camera.Camera;
import view.render.AbstractRenderer;

import java.awt.*;

public class BuildingRenderer extends AbstractRenderer {

    public BuildingRenderer(GameState gameState, Camera camera) {
        super(gameState , camera);
    }

    @Override
    public void render(Graphics2D g2, int screenWidth, int screenHeight) {
        if (getGameState() == null || getCamera() == null) return;

        double hexSize = getCamera().getHexSize();
        double centerX = getCamera().getCenterX(screenWidth);
        double centerY = getCamera().getCenterY(screenHeight);

        double bSize = hexSize * 0.5;

        for (Building building : getGameState().getBuildings()) {
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
}