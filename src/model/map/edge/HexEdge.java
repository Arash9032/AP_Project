package model.map.edge;


public class HexEdge {
    private final boolean hasRiver;
    private Wall wall = null;

    public HexEdge(boolean hasRiver) {
        this.hasRiver = hasRiver;
    }

    public boolean hasRiver() {
        return hasRiver;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }
}