package model.building;

public class ProductionTask {
    private final Producible item;
    private int turnsRemaining;

    public ProductionTask(Producible item) {
        this.item = item;
        this.turnsRemaining = item.getTurnCost();
    }

    public void advanceTurn() {
        if (turnsRemaining > 0) {
            turnsRemaining--;
        }
    }

    public boolean isComplete() {
        return turnsRemaining <= 0;
    }

    public Producible getItem() {
        return item;
    }

    public int getTurnsRemaining() {
        return turnsRemaining;
    }
}
