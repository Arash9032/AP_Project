public enum TerrainType {
    FOREST(2),
    PLAIN(1),
    MOUNTAIN(4),
    MEADOW(1);

    private final int movementCost;

    TerrainType(int movementCost) {
        this.movementCost = movementCost;
    }

    public int getMovementCost() {
        return movementCost;
    }
}