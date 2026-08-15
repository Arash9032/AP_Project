package view.render.unit;

import config.Constants;
import model.GameState;
import model.map.hex.Hex;
import model.unit.Unit;
import util.HexMath;
import view.camera.Camera;
import view.render.AbstractRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitRenderer extends AbstractRenderer {

    private final Map<Hex, List<Unit>> unitsByHex = new HashMap<>();

    public UnitRenderer(GameState gameState, Camera camera) {
        super(gameState , camera);
    }

    public void render(Graphics2D g2, int screenWidth, int screenHeight) {
        if (getGameState() == null || getCamera() == null) return;

        double hexSize = getCamera().getHexSize();
        double centerX = getCamera().getCenterX(screenWidth);
        double centerY = getCamera().getCenterY(screenHeight);

        double uSize = hexSize * 0.8;
        double orbitRadius = hexSize * 0.6;

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

            double cx = centerX + hexSize * HexMath.SQRT_3 * (q + r / 2.0);
            double cy = centerY + hexSize * 3.0 / 2.0 * r;

            int count = units.size();
            for (int i = 0; i < count; i++) {
                Unit unit = units.get(i);

                double angle = (count == 1) ? (Math.PI / 2) : (i * 2 * Math.PI / count);

                double ux = cx + orbitRadius * Math.cos(angle);
                double uy = cy + orbitRadius * Math.sin(angle);

                g2.translate(ux, uy);

                UnitTypeRenderer.getFromType(unit.getUnitType()).drawUnit(
                        g2,
                        uSize,
                        unit.getHp(),
                        unit.getMaximumHp(),
                        hexSize >= Constants.MINIMUM_DETAILED_SIZE
                );

                g2.translate(-ux, -uy);
            }
        }
    }
}