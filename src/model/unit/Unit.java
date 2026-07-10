package model.unit;

import model.hex.Hex;

public abstract class Unit {
    protected Hex currentHex;
    protected final int maxAP;
    protected int currentAP;
    protected final int visionRadius;

    public Unit(Hex startingHex, int maxAP, int visionRadius) {
        this.currentHex = startingHex;
        this.maxAP = maxAP;
        this.currentAP = maxAP;
        this.visionRadius = visionRadius;
    }

    public boolean move(Hex destination) {
        int cost = destination.getTerrain().getMovementCost();

        if (this.currentAP >= cost && this.currentHex.isAdjacentTo(destination)) {
            this.currentAP -= cost;
            this.currentHex = destination;
            return true;
        }
        return false;
    }

    public void resetAP() {
        this.currentAP = maxAP;
    }

    public Hex getCurrentHex() {
        return currentHex;
    }

    public int getCurrentAP() {
        return currentAP;
    }

    public int getMaxAP() {
        return maxAP;
    }

    public int getVisionRadius() {
        return visionRadius;
    }
}