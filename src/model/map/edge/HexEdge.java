package model.map.edge;


public class HexEdge {
    private boolean hasRiver;
    private Wall wall = null;

    public HexEdge(boolean hasRiver) {
        this.hasRiver = hasRiver;
    }

    public boolean hasRiver() {
        return hasRiver;
    }

    public void setHasRiver(boolean hasRiver) {
        this.hasRiver = hasRiver;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }
}