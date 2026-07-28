package model.command;

public interface ProductionCommand extends Command{
    public void cancel();
    public int getRemainingTurns();
    public void decrementTurn();
    public boolean isDone();
}
