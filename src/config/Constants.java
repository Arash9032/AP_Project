package config;

import java.awt.*;

public final class Constants {

    private Constants() {}

    public static final int FOREST_MOVEMENT_COST = 2;
    public static final int PLAIN_MOVEMENT_COST = 1;
    public static final int MOUNTAIN_MOVEMENT_COST = 4;
    public static final int MEADOW_MOVEMENT_COST = 1;
    public static final int MOUNTAIN_RANGE_MOVEMENT_COST = 1000;
    public static final int SEA_MOVEMENT_COST = 2;
    public static final int RIVER_CROSSING_PENALTY = 1;

    public static final int EXPLORER_MAX_HP = 30;
    public static final int EXPLORER_MAX_AP = 6;
    public static final int EXPLORER_VISION_RADIUS = 3;
    public static final int EXPLORER_PRODUCTION_TURN_COST = 2;

    public static final int BUILDER_MAX_HP = 30;
    public static final int BUILDER_MAX_AP = 4;
    public static final int BUILDER_VISION_RADIUS = 2;
    public static final int BUILDER_INITIAL_CHARGES = 3;
    public static final int BUILDER_PRODUCTION_TURN_COST = 2;

    public static final int WORKER_MAX_HP = 30;
    public static final int WORKER_MAX_AP = 4;
    public static final int WORKER_VISION_RADIUS = 1;
    public static final int WORKER_PRODUCTION_TURN_COST = 2;

    public static final int BORDER_EXPANDER_MAX_HP = 30;
    public static final int BORDER_EXPANDER_MAX_AP = 4;
    public static final int BORDER_EXPANDER_VISION_RADIUS = 1;
    public static final int BORDER_EXPANDER_PRODUCTION_TURN_COST = 3;

    public static final int LEVEL_1_TOWN_HALL_STORAGE_CAPACITY = 100;
    public static final int LEVEL_1_TOWN_HALL_MILITARY_UNIT_CAPACITY = 5;

    public static final int LEVEL_2_TOWN_HALL_STORAGE_CAPACITY = 200;
    public static final int LEVEL_2_TOWN_HALL_MILITARY_UNIT_CAPACITY = 10;
    public static final int LEVEL_2_TOWN_HALL_UPGRADE_TURN_COST = 3;
    public static final int LEVEL_2_TOWN_HALL_UPGRADE_WOOD_COST = 50;
    public static final int LEVEL_2_TOWN_HALL_UPGRADE_STONE_COST = 50;
    public static final int LEVEL_2_TOWN_HALL_UPGRADE_HEAL_AMOUNT = 50;

    public static final int LEVEL_3_TOWN_HALL_STORAGE_CAPACITY = 300;
    public static final int LEVEL_3_TOWN_HALL_MILITARY_UNIT_CAPACITY = 15;
    public static final int LEVEL_3_TOWN_HALL_UPGRADE_TURN_COST = 5;
    public static final int LEVEL_3_TOWN_HALL_UPGRADE_STONE_COST = 100;
    public static final int LEVEL_3_TOWN_HALL_UPGRADE_IRON_COST = 50;
    public static final int LEVEL_3_TOWN_HALL_UPGRADE_HEAL_AMOUNT = 0;

    public static final int INITIAL_UNIT_CAP = 5;
    public static final int INITIAL_CROPS = 100;
    public static final int INITIAL_WOOD = 100;
    public static final int INITIAL_STONE = 50;
    public static final int INITIAL_IRON = 0;

    public static final int SAFEGUARD_WOOD_PRODUCTION = 1;
    public static final int SAFEGUARD_CROPS_PRODUCTION = 1;

    public static final int TOWN_HALL_INITIAL_HP = 200;
    public static final int LUMBER_MILL_MAXIMUM_HP = 80;
    public static final int FARM_MAXIMUM_HP = 60;
    public static final int STONE_MINE_MAXIMUM_HP = 100;
    public static final int IRON_MINE_MAXIMUM_HP = 120;
    public static final int STABLE_MAXIMUM_HP = 100;
    public static final int VILLAGE_MAXIMUM_HP = 150;
    public static final int TOWN_MAXIMUM_HP = 250;
    public static final int WALL_MAXIMUM_HP = 50;

    public static final int WALL_CONSTRUCTION_AP_COST = 1;
    public static final int WALL_WOOD_COST = 10;
    public static final int WALL_WOOD_UPKEEP = 1;
    public static final int WALL_STONE_COST = 10;
    public static final int WALL_STONE_UPKEEP = 1;
    
    public static final int LUMBER_MILL_CONSTRUCTION_AP_COST = 1;
    public static final int LUMBER_MILL_WOOD_COST = 20;
    public static final int LUMBER_MILL_WOOD_UPKEEP = 2;
    public static final int LUMBER_MILL_WORKER_CAPACITY = 2;
    public static final int LUMBER_MILL_BASE_PRODUCTION_RATE = 5;

    public static final int FARM_CONSTRUCTION_AP_COST = 1;
    public static final int FARM_WOOD_COST = 15;
    public static final int FARM_WOOD_UPKEEP = 2;
    public static final int FARM_WORKER_CAPACITY = 3;
    public static final int FARM_BASE_PRODUCTION_RATE = 10;

    public static final int STONE_MINE_CONSTRUCTION_AP_COST = 2;
    public static final int STONE_MINE_WOOD_COST = 30;
    public static final int STONE_MINE_WOOD_UPKEEP = 3;
    public static final int STONE_MINE_WORKER_CAPACITY = 3;
    public static final int STONE_MINE_BASE_PRODUCTION_RATE = 4;

    public static final int IRON_MINE_CONSTRUCTION_AP_COST = 2;
    public static final int IRON_MINE_WOOD_COST = 40;
    public static final int IRON_MINE_WOOD_UPKEEP = 4;
    public static final int IRON_MINE_WORKER_CAPACITY = 2;
    public static final int IRON_MINE_BASE_PRODUCTION_RATE = 2;

    public static final int STABLE_CONSTRUCTION_AP_COST = 2;
    public static final int STABLE_WOOD_COST = 50;
    public static final int STABLE_WOOD_UPKEEP = 5;
    public static final int STABLE_WORKER_CAPACITY = 2;
    public static final int STABLE_BASE_PRODUCTION_RATE = 8;

    public static final int VILLAGE_CONSTRUCTION_AP_COST = 2;
    public static final int VILLAGE_WOOD_COST = 50;
    public static final int VILLAGE_STONE_COST = 50;
    public static final int VILLAGE_IRON_COST = 10;
    public static final int VILLAGE_WOOD_UPKEEP = 5;
    public static final int VILLAGE_STONE_UPKEEP = 5;
    public static final int VILLAGE_IRON_UPKEEP = 1;

    public static final int TOWN_CONSTRUCTION_AP_COST = 3;
    public static final int TOWN_WOOD_COST = 100;
    public static final int TOWN_STONE_COST = 100;
    public static final int TOWN_IRON_COST = 20;
    public static final int TOWN_WOOD_UPKEEP = 10;
    public static final int TOWN_STONE_UPKEEP = 10;
    public static final int TOWN_IRON_UPKEEP = 2;

    public static final int FRAME_WIDTH = 1400;
    public static final int FRAME_HEIGHT = 800;

    public static final Color GAME_BACKGROUND_COLOR = new Color(90, 0, 174);
    public static final Color BUTTON_BACKGROUND_COLOR = new Color(112, 0, 112);
    public static final Color BUTTON_BORDER_COLOR = new Color(255, 0, 221);
    public static final Color BUTTON_TEXT_COLOR = new Color(255, 255, 255);

    public static final int MAP_RADIUS = 15;

    public static final int MOUNTAIN_RANGE_COUNT = 10;
    public static final int MOUNTAIN_RANGE_LENGTH = 10;

    public static final int REGION_SEED_COUNT = 4;
    public static final int REGION_EXPANSION_ITERATIONS = 4;
    public static final double REGION_EXPANSION_PROBABILITY = 0.75;

    public static final int FOREST_RESOURCE_PROBABILITY = 1;
    public static final int FOREST_RESOURCE_CAPACITY = 100;

    public static final double MOUNTAIN_RESOURCE_PROBABILITY = 0.6;
    public static final int MOUNTAIN_RESOURCE_CAPACITY = 80;

    public static final double PLAIN_RESOURCE_PROBABILITY = 0.4;
    public static final int PLAIN_RESOURCE_CAPACITY = 60;

    public static final double MEADOW_RESOURCE_PROBABILITY = 0.4;
    public static final int MEADOW_RESOURCE_CAPACITY = 60;

    public static final double SEA_RESOURCE_PROBABILITY = 0.3;
    public static final int SEA_RESOURCE_CAPACITY = 50;

    public static final int TOWN_HALL_SAFE_RADIUS = 2;
    public static final int EXPANSION_PATH_COUNT = 3;

    public static final Color FOREST_HEX_COLOR = new Color(16, 124, 65);
    public static final Color PLAIN_HEX_COLOR = new Color(225, 190, 120);
    public static final Color MOUNTAIN_HEX_COLOR = new Color(160, 150, 180);
    public static final Color MEADOW_HEX_COLOR = new Color(110, 220, 70);
    public static final Color MOUNTAIN_RANGE_HEX_COLOR = new Color(55, 40, 75);
    public static final Color SEA_HEX_COLOR = new Color(0, 180, 235);
    public static final Color HEX_BORDER_COLORS = new Color(55, 0, 105);
}