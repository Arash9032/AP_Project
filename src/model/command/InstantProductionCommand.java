package model.command;

import model.unit.Builder;

public abstract class InstantProductionCommand implements Command {
    private final Builder builder;
    private final int apCost;

    public InstantProductionCommand(Builder builder, int apCost) {
        this.builder = builder;
        this.apCost = apCost;
    }

    public Builder getBuilder() {
        return builder;
    }

    public int getApCost() {
        return apCost;
    }
}