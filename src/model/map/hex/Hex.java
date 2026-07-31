package model.map.hex;

public class Hex {
    private final Point coordinate;

    private final TerrainType terrain;
    private final HexResource resource;
    private int resourceCapacity;

    private boolean isExplored;
    private boolean isWithinBorder;

    public Hex(int q, int r, TerrainType terrain, HexResource resource, int resourceCapacity) {
        this.coordinate = new Point(q , r);
        this.terrain = terrain;
        this.resource = resource;
        this.resourceCapacity = resourceCapacity;

        this.isExplored = false;
        this.isWithinBorder = false;
    }

    public int distanceTo(Hex other) {
        int q1 = this.coordinate.getQ();
        int r1 = this.coordinate.getR();

        int q2 = other.coordinate.getQ();
        int r2 = other.coordinate.getR();

        return (Math.abs(q1 - q2) + Math.abs(q1 + r1 - q2 - r2) + Math.abs(r1 - r2)) / 2;
    }

    public boolean isAdjacentTo(Hex other) {
        return distanceTo(other) == 1;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public TerrainType getTerrain() {
        return terrain;
    }

    public HexResource getResource() {
        return resource;
    }

    public int getResourceCapacity() {
        return resourceCapacity;
    }

    public void setResourceCapacity(int resourceCapacity) {
        this.resourceCapacity = resourceCapacity;
    }

    public int depleteResource(int amount) {
        if (amount <= 0 || this.resourceCapacity <= 0) {
            return 0;
        }

        int actualExtracted = Math.min(this.resourceCapacity, amount);
        this.resourceCapacity -= actualExtracted;

        return actualExtracted;
    }

    public boolean isDepleted() {
        return this.resourceCapacity <= 0;
    }

    public boolean isExplored() {
        return isExplored;
    }

    public void setExplored(boolean explored) {
        isExplored = explored;
    }

    public boolean isWithinBorder() {
        return isWithinBorder;
    }

    public void setWithinBorder(boolean withinBorder) {
        isWithinBorder = withinBorder;
    }
}