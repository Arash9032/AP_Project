package view.render;

import model.GameState;
import view.camera.Camera;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ShadowRenderer extends AbstractRenderer {

    private final Color shadowColor = new Color(0, 0, 0, 160);
    private final Rectangle2D.Double screenRect = new Rectangle2D.Double();

    public ShadowRenderer(GameState gameState, Camera camera, RenderContext renderContext) {
        super(gameState, camera, renderContext);
    }

    @Override
    public void render(Graphics2D g2, int screenWidth, int screenHeight) {
        if (!getRenderContext().isResourceOverlayActive()) return;

        double zoom = getCamera().getHexSize() / WorldRenderer.BASE_HEX_SIZE;
        double cx = getCamera().getCenterX(screenWidth);
        double cy = getCamera().getCenterY(screenHeight);

        double x = -cx / zoom;
        double y = -cy / zoom;
        double w = screenWidth / zoom;
        double h = screenHeight / zoom;

        screenRect.setRect(x, y, w, h);

        Paint originalPaint = g2.getPaint();

        g2.setColor(shadowColor);
        g2.fill(screenRect);

        g2.setPaint(originalPaint);
    }
}