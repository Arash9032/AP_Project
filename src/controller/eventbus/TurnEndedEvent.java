package controller.eventbus;

public class TurnEndedEvent implements GameEvent {
    private final int currentTurn;

    public TurnEndedEvent(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}