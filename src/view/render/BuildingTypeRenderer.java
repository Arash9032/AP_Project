package view.render;

import model.map.building.BuildingType;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public enum BuildingTypeRenderer {

    LUMBER_MILL(BuildingType.LUMBER_MILL) {
        @Override
        public void drawShape(Graphics2D g2, double size) {

        }
    },
    FARM(BuildingType.FARM) {
        @Override
        public void drawShape(Graphics2D g2, double size) {

        }
    },
    STONE_MINE(BuildingType.STONE_MINE) {
        @Override
        public void drawShape(Graphics2D g2, double size) {

        }
    },
    IRON_MINE(BuildingType.IRON_MINE) {
        @Override
        public void drawShape(Graphics2D g2, double size) {

        }
    },
    STABLE(BuildingType.STABLE) {
        @Override
        public void drawShape(Graphics2D g2, double size) {

        }
    },
    VILLAGE(BuildingType.VILLAGE) {
        @Override
        public void drawShape(Graphics2D g2, double size) {

        }
    },
    TOWN(BuildingType.TOWN) {
        @Override
        public void drawShape(Graphics2D g2, double size) {

        }
    },
    TOWN_HALL(BuildingType.TOWN_HALL) {
        @Override
        public void drawShape(Graphics2D g2, double size) {

        }
    };

    private static final Map<BuildingType, BuildingTypeRenderer> MAP = new EnumMap<>(BuildingType.class);

    static{
        for(BuildingTypeRenderer e : BuildingTypeRenderer.values()){
            MAP.put(e.getType() , e);
        }
    }

    private final BuildingType type;

    BuildingTypeRenderer(BuildingType type) {
        this.type = type;
    }

    public BuildingType getType() {
        return type;
    }

    public static BuildingTypeRenderer getFromType(BuildingType type){
        BuildingTypeRenderer renderer = MAP.get(type);
        if(renderer != null)
            return renderer;
        throw new IllegalStateException("BuildingType " + type.name() + " doesn't have a BuildingTypeRenderer.");
    }

    protected abstract void drawShape(Graphics2D g2, double size);

    private void drawHealthBar(Graphics2D g2, double size, int hp){

    }

    public void drawBuilding(Graphics2D g2, double size, int hp){
        drawShape(g2, size);
        drawHealthBar(g2, size, hp);
    }
}