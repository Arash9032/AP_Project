package model.command;

public interface Command {
    public boolean execute(); // returns true if it's undoable.
}
