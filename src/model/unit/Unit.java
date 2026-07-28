package model.unit;

import model.map.hex.Hex;

public abstract class Unit {
    private final UnitType unitType;
    private Hex currentHex;
    private final int maxAP;
    private int currentAP;
    private final int visionRadius;

    public Unit(Hex startingHex, int maxAP, int visionRadius, UnitType unitType) {
        this.currentHex = startingHex;
        this.maxAP = maxAP;
        this.currentAP = maxAP;
        this.visionRadius = visionRadius;
        this.unitType = unitType;
    }

    public boolean move(Hex destination) {

        int cost = destination.getTerrain().getMovementCost();
        if (this.currentAP < cost || !this.currentHex.isAdjacentTo(destination)) return false;

        this.currentAP -= cost;
        this.currentHex = destination;
        return true;
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

    public void setCurrentAP(int currentAP) {
        this.currentAP = currentAP;
    }

    public int getMaxAP() {
        return maxAP;
    }

    public int getVisionRadius() {
        return visionRadius;
    }

    public UnitType getUnitType() {
        return unitType;
    }
}