package view.render.building;

import config.Constants;
import model.GameState;
import model.map.building.Building;
import model.map.hex.Hex;
import util.HexMath;
import view.camera.Camera;
import view.render.AbstractRenderer;
import view.render.RenderContext;

import java.awt.*;

public class BuildingRenderer extends AbstractRenderer {

    private static final double BASE_HEX_SIZE = 100.0;

    public BuildingRenderer(GameState gameState, Camera camera, RenderContext renderContext) {
        super(gameState, camera, renderContext);
    }

    @Override
    public void render(Graphics2D g2, int screenWidth, int screenHeight) {
        if (getGameState() == null || getCamera() == null) return;

        double hexSize = getCamera().getHexSize();
        double zoom = hexSize / BASE_HEX_SIZE;
        double centerX = getCamera().getCenterX(screenWidth);
        double centerY = getCamera().getCenterY(screenHeight);

        double bSize = BASE_HEX_SIZE * 0.5;

        for (Building building : getGameState().getBuildings()) {
            Hex hex = building.getLocation();
            if (hex == null) continue;

            int q = hex.getCoordinate().getQ();
            int r = hex.getCoordinate().getR();

            double worldX = BASE_HEX_SIZE * HexMath.SQRT_3 * (q + r / 2.0);
            double worldY = BASE_HEX_SIZE * 3.0 / 2.0 * r;

            double screenX = centerX + worldX * zoom;
            double screenY = centerY + worldY * zoom;

            if (!isHexInSight(screenX, screenY, hexSize, screenWidth, screenHeight)) {
                continue;
            }

            g2.translate(worldX, worldY);

            BuildingTypeRenderer.getFromType(building.getType()).drawBuilding(
                    g2, bSize, building.getHp(), building.getMaximumHp(), getRenderContext().isDetailed(getCamera())
            );

            g2.translate(-worldX, -worldY);
        }
    }

    private boolean isHexInSight(double cx, double cy, double hexSize, int screenWidth, int screenHeight) {
        return !(cx + hexSize < 0 || cx - hexSize > screenWidth ||
                cy + hexSize < 0 || cy - hexSize > screenHeight);
    }
}