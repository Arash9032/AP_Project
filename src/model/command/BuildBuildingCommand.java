package model.command;

import model.map.building.Building;
import model.map.building.BuildingType;
import model.map.building.townhall.TownHall;
import model.map.hex.Hex;
import model.unit.Builder;

public class BuildBuildingCommand extends InstantProductionCommand {

    private final BuildingType buildingType;
    private final Hex targetHex;
    private final TownHall townHall;

    public BuildBuildingCommand(Builder builder, BuildingType buildingType, Hex targetHex, TownHall townHall) {
        super(builder, buildingType.getConstructionApCost());
        if (builder == null || targetHex == null || townHall == null) {
            throw new IllegalArgumentException("Arguments cannot be null.");
        }
        this.buildingType = buildingType;
        this.targetHex = targetHex;
        this.townHall = townHall;
    }

    @Override
    public void execute() {
        if (getBuilder().getCharges() <= 0) {
            throw new IllegalStateException("Builder has no remaining charges.");
        }
        if (getBuilder().getCurrentAP() < getApCost()) {
            throw new IllegalStateException("Not enough AP to build.");
        }
        if (!townHall.getInventory().hasEnoughResources(buildingType.getConstructionCost())) {
            throw new IllegalStateException("Not enough resources to build.");
        }

        getBuilder().setCurrentAP(getBuilder().getCurrentAP() - getApCost());
        getBuilder().consumeCharge();
        townHall.getInventory().consumeResources(buildingType.getConstructionCost());

        Building newBuilding = buildingType.createBuilding(targetHex);

        if (getBuilder().isConsumed()) {
            getBuilder().setHp(0);
        }
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public Hex getTargetHex() {
        return targetHex;
    }

    public TownHall getTownHall() {
        return townHall;
    }
}