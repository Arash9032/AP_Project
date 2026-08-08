package controller.eventbus;

public class TurnEndedEvent implements GameEvent {
    private final int newTurn;

    public TurnEndedEvent(int newTurn) {
        this.newTurn = newTurn;
    }

    public int getNewTurn() {
        return newTurn;
    }
}