package view.render;

import model.GameState;
import view.camera.Camera;

import java.awt.*;

public class WorldRenderer {
    private GameState gameState;
    private Camera camera;
    private HexRenderer hexRenderer;

    public WorldRenderer(GameState gameState, Camera camera){
        this.gameState = gameState;
        this.camera = camera;
        hexRenderer = new HexRenderer(gameState, camera);
    }

    public void renderWorld(Graphics2D g2, int screenWidth, int screenHeight){
        hexRenderer.render(g2, screenWidth, screenHeight);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        hexRenderer.setGameState(gameState);
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public HexRenderer getHexRenderer() {
        return hexRenderer;
    }

    public void setHexRenderer(HexRenderer hexRenderer) {
        this.hexRenderer = hexRenderer;
    }
}
