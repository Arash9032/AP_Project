package model.map;

import config.Constants;
import model.map.edge.EdgeKey;
import model.map.edge.HexEdge;
import model.map.hex.Hex;
import model.map.hex.HexResource;
import model.map.hex.Point;
import model.map.hex.TerrainType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class MapGenerator {

    private MapGenerator(){}

    private static final Random RANDOM = new Random();

    public static GameMap generateMap() {
        GameMap map = new GameMap();
        generateBlankGrid(map);
        generateMountainRanges(map);
        generateRegionSeeds(map);
        expandRegions(map);
        assignResources(map);
        ensureTownHallConstraints(map);
        generateEdges(map);
        generateRivers(map);
        return map;
    }

    private static void generateBlankGrid(GameMap map) {
        for (int q = -Constants.MAP_RADIUS; q <= Constants.MAP_RADIUS; q++) {
            int r1 = Math.max(-Constants.MAP_RADIUS, -q - Constants.MAP_RADIUS);
            int r2 = Math.min(Constants.MAP_RADIUS, -q + Constants.MAP_RADIUS);
            for (int r = r1; r <= r2; r++) {
                updateHexTerrain(map, new Point(q, r), TerrainType.MEADOW);
            }
        }
    }

    private static void generateMountainRanges(GameMap map) {
        List<Point> allPoints = new ArrayList<>(map.getHexes().keySet());

        for (int i = 0; i < Constants.MOUNTAIN_RANGE_COUNT; i++) {
            Point currentPoint = getRandomElement(allPoints);

            for (int step = 0; step < Constants.MOUNTAIN_RANGE_LENGTH; step++) {
                if (currentPoint.getQ() != 0 || currentPoint.getR() != 0) {
                    updateHexTerrain(map, currentPoint, TerrainType.MOUNTAIN_RANGE);
                }

                List<Hex> neighbors = map.getNeighbors(currentPoint);
                if (neighbors.isEmpty()) {
                    break;
                }
                currentPoint = getRandomElement(neighbors).getCoordinate();
            }
        }
    }

    private static void generateRegionSeeds(GameMap map) {
        TerrainType[] regionTypes = {TerrainType.FOREST, TerrainType.MOUNTAIN, TerrainType.PLAIN, TerrainType.SEA};
        List<Point> allPoints = new ArrayList<>(map.getHexes().keySet());

        for (TerrainType terrainType : regionTypes) {
            for (int i = 0; i < Constants.REGION_SEED_COUNT; i++) {
                Point seedPoint = getRandomElement(allPoints);
                if (map.getHex(seedPoint).getTerrain() != TerrainType.MOUNTAIN_RANGE) {
                    updateHexTerrain(map, seedPoint, terrainType);
                }
            }
        }
    }

    private static void expandRegions(GameMap map) {
        for (int i = 0; i < Constants.REGION_EXPANSION_ITERATIONS; i++) {
            Map<Point, TerrainType> nextTerrains = new HashMap<>();

            for (Hex hex : map.getHexes().values()) {
                if (hex.getTerrain() == TerrainType.MOUNTAIN_RANGE) {
                    continue;
                }

                TerrainType dominantTerrain = getDominantNeighborTerrain(map, hex);
                if (dominantTerrain != null && RANDOM.nextDouble() < Constants.REGION_EXPANSION_PROBABILITY) {
                    nextTerrains.put(hex.getCoordinate(), dominantTerrain);
                }
            }

            for (Map.Entry<Point, TerrainType> entry : nextTerrains.entrySet()) {
                updateHexTerrain(map, entry.getKey(), entry.getValue());
            }
        }
    }

    private static void assignResources(GameMap map) {
        for (Hex hex : map.getHexes().values()) {
            TerrainType terrainType = hex.getTerrain();
            HexResource resource = terrainType.generateResource(RANDOM);
            int capacity = (resource == HexResource.NONE) ? 0 : terrainType.getResourceCapacity();

            updateHexTerrain(map, hex.getCoordinate(), terrainType, resource, capacity);
        }
    }

    private static void ensureTownHallConstraints(GameMap map) {
        Point center = new Point(0, 0);
        updateHexTerrain(map, center, TerrainType.PLAIN);

        boolean hasForest = false;
        List<Hex> withinRadiusTwo = getHexesWithinRadius(map, center, 1, Constants.TOWN_HALL_SAFE_RADIUS);

        for (Hex hex : withinRadiusTwo) {
            TerrainType currentTerrain = hex.getTerrain();

            if (currentTerrain == TerrainType.MOUNTAIN_RANGE || currentTerrain == TerrainType.SEA) {
                updateHexTerrain(map, hex.getCoordinate(), TerrainType.MEADOW);
            } else if (currentTerrain == TerrainType.FOREST) {
                hasForest = true;
            }
        }

        if (!hasForest && !withinRadiusTwo.isEmpty()) {
            Hex targetHex = getRandomElement(withinRadiusTwo);
            updateHexTerrain(map, targetHex.getCoordinate(), TerrainType.FOREST, HexResource.WOOD, Constants.FOREST_RESOURCE_CAPACITY);
        }

        ensureExpansionPaths(map);
    }

    private static void ensureExpansionPaths(GameMap map) {
        Point center = new Point(0, 0);

        for (int i = 0; i < Constants.EXPANSION_PATH_COUNT; i++) {
            Point currentPoint = center;
            for (int step = 0; step < Constants.MAP_RADIUS; step++) {
                List<Hex> outwardNeighbors = getOutwardNeighbors(map, currentPoint, center, step);

                if (!outwardNeighbors.isEmpty()) {
                    currentPoint = getRandomElement(outwardNeighbors).getCoordinate();
                    clearObstacles(map, currentPoint);
                }
            }
        }
    }

    private static TerrainType getDominantNeighborTerrain(GameMap map, Hex hex) {
        Map<TerrainType, Integer> terrainCounts = new HashMap<>();

        for (Hex neighbor : map.getNeighbors(hex)) {
            TerrainType neighborTerrain = neighbor.getTerrain();
            if (neighborTerrain != TerrainType.MOUNTAIN_RANGE && neighborTerrain != TerrainType.MEADOW) {
                terrainCounts.put(neighborTerrain, terrainCounts.getOrDefault(neighborTerrain, 0) + 1);
            }
        }

        if (terrainCounts.isEmpty()) {
            return null;
        }

        TerrainType dominantTerrain = null;
        int maxCount = 0;

        for (Map.Entry<TerrainType, Integer> entry : terrainCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                dominantTerrain = entry.getKey();
            } else if (entry.getValue() == maxCount && RANDOM.nextBoolean()) {
                dominantTerrain = entry.getKey();
            }
        }

        return dominantTerrain;
    }

    private static List<Hex> getHexesWithinRadius(GameMap map, Point center, int minRadius, int maxRadius) {
        List<Hex> withinRadius = new ArrayList<>();
        for (Hex hex : map.getHexes().values()) {
            int distance = hex.distanceTo(map.getHex(center));
            if (distance >= minRadius && distance <= maxRadius) {
                withinRadius.add(hex);
            }
        }
        return withinRadius;
    }

    private static List<Hex> getOutwardNeighbors(GameMap map, Point currentPoint, Point center, int currentStep) {
        List<Hex> outwardNeighbors = new ArrayList<>();
        for (Hex neighbor : map.getNeighbors(currentPoint)) {
            if (neighbor.distanceTo(map.getHex(center)) > currentStep) {
                outwardNeighbors.add(neighbor);
            }
        }
        return outwardNeighbors;
    }

    private static void clearObstacles(GameMap map, Point point) {
        Hex currentHex = map.getHex(point);
        if (currentHex.getTerrain() == TerrainType.MOUNTAIN_RANGE || currentHex.getTerrain() == TerrainType.SEA) {
            updateHexTerrain(map, point, TerrainType.MEADOW);
        }
    }

    private static void updateHexTerrain(GameMap map, Point coordinate, TerrainType terrainType) {
        map.addHex(new Hex(coordinate.getQ(), coordinate.getR(), terrainType, HexResource.NONE, 0));
    }

    private static void updateHexTerrain(GameMap map, Point coordinate, TerrainType terrainType, HexResource resource, int capacity) {
        map.addHex(new Hex(coordinate.getQ(), coordinate.getR(), terrainType, resource, capacity));
    }

    private static <T> T getRandomElement(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    private static void generateEdges(GameMap map){
        for(Point p1 : map.getHexes().keySet()){
            for(Hex neighbor : map.getNeighbors(p1)){
                Point p2 = neighbor.getCoordinate();
                EdgeKey edgeKey = new EdgeKey(p1 , p2);
                if(map.getEdge(edgeKey) == null){
                    map.addEdge(edgeKey, new HexEdge(false));
                }
            }
        }
    }

    private static void generateRivers(GameMap map) {
        List<EdgeKey> allEdges = new ArrayList<>(map.getEdges().keySet());

        for (int i = 0; i < Constants.RIVER_COUNT; i++) {
            EdgeKey currentEdgeKey = getRandomElement(allEdges);
            int riverLength = Constants.MINIMUM_RIVER_LENGTH + RANDOM.nextInt(Constants.MAXIMUM_RIVER_LENGTH - Constants.MINIMUM_RIVER_LENGTH + 1);

            for (int step = 0; step < riverLength; step++) {
                HexEdge edge = map.getEdge(currentEdgeKey);
                if (edge != null) {
                    edge.setHasRiver(true);
                }

                List<EdgeKey> adjacentEdges = map.getAdjacentEdges(currentEdgeKey);

                adjacentEdges.removeIf(key -> {
                    HexEdge e = map.getEdge(key);
                    return e == null || e.hasRiver();
                });

                if (adjacentEdges.isEmpty()) {
                    break;
                }

                currentEdgeKey = getRandomElement(adjacentEdges);
            }
        }
    }
}