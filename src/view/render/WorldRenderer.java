package view.render;

import model.GameState;
import view.camera.Camera;
import view.render.building.BuildingRenderer;
import view.render.hex.HexRenderer;
import view.render.unit.UnitRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WorldRenderer {
    private GameState gameState;
    private Camera camera;
    private final List<AbstractRenderer> layers;

    public WorldRenderer(GameState gameState, Camera camera) {
        this.gameState = gameState;
        this.camera = camera;
        this.layers = new ArrayList<>();
        initLayers();
    }

    private void initLayers(){
        layers.add(new HexRenderer(gameState, camera));
        layers.add(new BuildingRenderer(gameState, camera));
        layers.add(new UnitRenderer(gameState , camera));
    }

    public void renderWorld(Graphics2D g2, int screenWidth, int screenHeight) {
        for (AbstractRenderer layer : layers) {
            layer.render(g2, screenWidth, screenHeight);
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        for (AbstractRenderer layer : layers) {
            layer.setGameState(gameState);
        }
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
        for (AbstractRenderer layer : layers) {
            layer.setCamera(camera);
        }
    }
}