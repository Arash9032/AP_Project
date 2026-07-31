package model.unit;

import model.map.Destructible;
import model.map.hex.Hex;

public abstract class Unit implements Destructible {
    private final UnitType unitType;
    private Hex currentHex;
    private final int maxAP;
    private int currentAP;
    private final int visionRadius;
    private int HP;

    public Unit(Hex startingHex, int maxAP, int visionRadius, UnitType unitType, int HP) {
        this.currentHex = startingHex;
        this.maxAP = maxAP;
        this.currentAP = maxAP;
        this.visionRadius = visionRadius;
        this.unitType = unitType;
        this.HP = HP;
    }

    public boolean move(Hex destination) {

        int cost = destination.getTerrain().getMovementCost();
        if (this.currentAP < cost || !this.currentHex.isAdjacentTo(destination)) return false;

        this.currentAP -= cost;
        this.currentHex = destination;
        return true;
    }

    public boolean undoMove(Hex previousHex){
        int cost = currentHex.getTerrain().getMovementCost();
        if(!this.currentHex.isAdjacentTo(previousHex)) return false;
        this.currentAP += cost;
        this.currentHex = previousHex;
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

    public void setCurrentHex(Hex currentHex) {
        this.currentHex = currentHex;
    }

    @Override
    public int getHp() {
        return HP;
    }

    @Override
    public void setHp(int hp) {
        this.HP = hp;
    }
}