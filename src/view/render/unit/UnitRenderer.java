package view.render.unit;

import config.Constants;
import model.GameState;
import model.map.hex.Hex;
import model.unit.Unit;
import util.HexMath;
import view.camera.Camera;
import view.render.AbstractRenderer;
import view.render.RenderContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitRenderer extends AbstractRenderer {

    private static final double BASE_HEX_SIZE = 100.0;
    private final Map<Hex, List<Unit>> unitsByHex = new HashMap<>();

    public UnitRenderer(GameState gameState, Camera camera, RenderContext renderContext) {
        super(gameState, camera, renderContext);
    }

    public void render(Graphics2D g2, int screenWidth, int screenHeight) {
        if (getGameState() == null || getCamera() == null) return;

        double hexSize = getCamera().getHexSize();
        double zoom = hexSize / BASE_HEX_SIZE;
        double centerX = getCamera().getCenterX(screenWidth);
        double centerY = getCamera().getCenterY(screenHeight);

        double uSize = BASE_HEX_SIZE * 0.8;
        double orbitRadius = BASE_HEX_SIZE * 0.6;

        for (List<Unit> list : unitsByHex.values()) {
            list.clear();
        }

        for (Unit unit : getGameState().getUnits()) {
            Hex hex = unit.getCurrentHex();
            if (hex == null) continue;

            unitsByHex.computeIfAbsent(hex, k -> new ArrayList<>()).add(unit);
        }

        for (Map.Entry<Hex, List<Unit>> entry : unitsByHex.entrySet()) {
            Hex hex = entry.getKey();
            List<Unit> units = entry.getValue();

            if (units.isEmpty()) continue;

            int q = hex.getCoordinate().getQ();
            int r = hex.getCoordinate().getR();

            double worldX = BASE_HEX_SIZE * HexMath.SQRT_3 * (q + r / 2.0);
            double worldY = BASE_HEX_SIZE * 3.0 / 2.0 * r;

            double screenX = centerX + worldX * zoom;
            double screenY = centerY + worldY * zoom;

            if (!isHexInSight(screenX, screenY, hexSize, screenWidth, screenHeight)) {
                continue;
            }

            int count = units.size();
            for (int i = 0; i < count; i++) {
                Unit unit = units.get(i);

                double angle = (count == 1) ? (Math.PI / 2) : (i * 2 * Math.PI / count);

                double ux = worldX + orbitRadius * Math.cos(angle);
                double uy = worldY + orbitRadius * Math.sin(angle);

                g2.translate(ux, uy);

                UnitTypeRenderer.getFromType(unit.getUnitType()).drawUnit(
                        g2,
                        uSize,
                        unit.getHp(),
                        unit.getMaximumHp(),
                        getRenderContext().isDetailed(getCamera()),
                        unit == getGameState().getSelectedUnit()
                );

                g2.translate(-ux, -uy);
            }
        }
    }

    private boolean isHexInSight(double cx, double cy, double hexSize, int screenWidth, int screenHeight) {
        return !(cx + hexSize < 0 || cx - hexSize > screenWidth ||
                cy + hexSize < 0 || cy - hexSize > screenHeight);
    }
}