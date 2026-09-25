package view.render.hex;

import config.Constants;
import model.GameState;
import model.map.hex.Hex;
import model.map.hex.HexResource;
import util.HexMath;
import view.camera.Camera;
import view.render.AbstractRenderer;
import view.render.RenderContext;

import java.awt.*;

public class ResourceRenderer extends AbstractRenderer {
    private final int resourceSize = 160;

    public ResourceRenderer(GameState gameState, Camera camera, RenderContext renderContext) {
        super(gameState, camera, renderContext);
    }

    @Override
    public void render(Graphics2D g2, int screenWidth, int screenHeight) {
        if(!getRenderContext().isResourceOverlayActive()) return;

        double hexSize = getCamera().getHexSize();
        double zoom = hexSize / Constants.BASE_HEX_SIZE;
        double centerX = getCamera().getCenterX(screenWidth);
        double centerY = getCamera().getCenterY(screenHeight);

        for(Hex hex : getGameState().getGameMap().getHexes().values()){
            if(hex.getResource()==HexResource.NONE) continue;
            double worldX = HexMath.getWorldX(hex.getCoordinate().getQ() , hex.getCoordinate().getR());
            double worldY = HexMath.getWorldY(hex.getCoordinate().getR());
            if (!HexMath.isHexInSight(centerX + worldX * zoom, centerY + worldY * zoom, hexSize, screenWidth, screenHeight)) {
                continue;
            }

            g2.translate(worldX , worldY);
            HexResourceRenderer.getFromResource(hex.getResource()).drawResourceIcon(g2, resourceSize);
            g2.translate(-worldX , -worldY);
        }
    }
}
