package model;

import model.ResourceType;
import model.TerrainType;

public class Hex {
    private final int q;
    private final int r;

    private final TerrainType terrain;
    private final ResourceType resource;
    private int resourceCapacity;

    private boolean isExplored;
    private boolean isWithinBorder;

    public Hex(int q, int r, TerrainType terrain, ResourceType resource, int resourceCapacity) {
        this.q = q;
        this.r = r;
        this.terrain = terrain;
        this.resource = resource;
        this.resourceCapacity = resourceCapacity;

        this.isExplored = false;
        this.isWithinBorder = false;
    }

    public int distanceTo(Hex other) {
        return (Math.abs(this.q - other.q) + Math.abs(this.q + this.r - other.q - other.r) + Math.abs(this.r - other.r)) / 2;
    }

    public boolean isAdjacentTo(Hex other) {
        return distanceTo(other) == 1;
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    public TerrainType getTerrain() {
        return terrain;
    }

    public ResourceType getResource() {
        return resource;
    }

    public int getResourceCapacity() {
        return resourceCapacity;
    }

    public void depleteResource(int amount) {
        this.resourceCapacity = Math.max(0, this.resourceCapacity - amount);
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