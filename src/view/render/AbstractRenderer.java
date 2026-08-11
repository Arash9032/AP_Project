package view.render;

import model.GameState;
import view.camera.Camera;

import java.awt.*;

public abstract class AbstractRenderer {
    private GameState gameState;
    private Camera camera;

    public AbstractRenderer(GameState gameState, Camera camera) {
        this.gameState = gameState;
        this.camera = camera;
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
}