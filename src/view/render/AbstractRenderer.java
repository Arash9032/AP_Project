package view.render;

import model.GameState;
import view.camera.Camera;

import java.awt.*;

public abstract class AbstractRenderer {
    private GameState gameState;
    private Camera camera;
    private RenderContext renderContext;

    public AbstractRenderer(GameState gameState, Camera camera, RenderContext renderContext) {
        this.gameState = gameState;
        this.camera = camera;
        this.renderContext = renderContext;
    }

    public abstract void render(Graphics2D g2, int screenWidth, int screenHeight);

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

    public RenderContext getRenderContext() {
        return renderContext;
    }

    public void setRenderContext(RenderContext renderContext) {
        this.renderContext = renderContext;
    }
}