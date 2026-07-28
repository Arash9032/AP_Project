package model.command;

import model.map.building.Building;
import model.map.building.BuildingType;
import model.map.hex.Hex;
import model.unit.Builder;

public class BuildBuildingCommand implements Command {

    private final Builder builder;
    private final BuildingType buildingType;
    private final Hex targetHex;

    public BuildBuildingCommand(Builder builder, BuildingType buildingType, Hex targetHex) {
        if (builder == null || buildingType == null || targetHex == null) {
            throw new IllegalArgumentException("Builder, BuildingType, and Target Hex cannot be null.");
        }
        this.builder = builder;
        this.buildingType = buildingType;
        this.targetHex = targetHex;
    }

    @Override
    public boolean execute() {
        if (builder.getCharges() <= 0) {
            throw new IllegalStateException("Builder has no remaining charges.");
        }
        if (builder.getCurrentAP() < buildingType.getApCost()) {
            throw new IllegalStateException("Not enough AP to build " + buildingType);
        }

        builder.setCurrentAP(builder.getCurrentAP() - buildingType.getApCost());
        builder.consumeCharge();

        Building newBuilding = buildingType.createBuilding(targetHex);
        // TODO:

        return false;
    }

    public Builder getBuilder() {
        return builder;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public Hex getTargetHex() {
        return targetHex;
    }
}