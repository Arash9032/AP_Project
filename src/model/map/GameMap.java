package model.map;

import model.map.edge.EdgeKey;
import model.map.edge.HexEdge;
import model.map.hex.Hex;
import model.map.hex.Point;

import java.util.*;

public class GameMap {
    private final Map<Point, Hex> hexes;
    private final Map<EdgeKey, HexEdge> edges;

    private static final int[][] NEIGHBOR_DIRECTIONS = {
            {1, 0}, {1, -1}, {0, -1}, {-1, 0}, {-1, 1}, {0, 1}
    };

    public GameMap() {
        this.hexes = new HashMap<>();
        this.edges = new HashMap<>();
    }

    public void addHex(Hex hex) {
        hexes.put(hex.getCoordinate(), hex);
    }

    public Hex getHex(Point point) {
        return hexes.get(point);
    }

    public Hex getHex(int q, int r) {
        return getHex(new Point(q , r));
    }

    public void addEdge(EdgeKey key , HexEdge edge){
        edges.put(key , edge);
    }

    public void addEdge(Point p1, Point p2, HexEdge edge) {
        addEdge(new EdgeKey(p1 , p2) , edge);
    }

    public HexEdge getEdge(EdgeKey key){
        return edges.get(key);
    }

    public HexEdge getEdge(Point p1, Point p2) {
        return getEdge(new EdgeKey(p1 , p2));
    }

    public HexEdge getEdge(int q1 , int r1, int q2, int r2){
        return getEdge(new Point(q1 , r1) , new Point(q2 , r2));
    }

    public List<Hex> getNeighbors(Point point) {
        List<Hex> neighbors = new ArrayList<>();
        for (int[] direction : NEIGHBOR_DIRECTIONS) {
            int neighborQ = point.getQ() + direction[0];
            int neighborR = point.getR() + direction[1];

            Hex neighbor = getHex(neighborQ, neighborR);
            if (neighbor != null) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    public List<Hex> getNeighbors(Hex hex){
        return getNeighbors(hex.getCoordinate());
    }

    public boolean areAdjacent(Point p1, Point p2) {
        Hex h1 = getHex(p1);
        Hex h2 = getHex(p2);
        if (h1 == null || h2 == null) {
            return false;
        }
        return h1.isAdjacentTo(h2);
    }

    public Map<Point, Hex> getHexes() {
        return Collections.unmodifiableMap(hexes);
    }

    public Map<EdgeKey, HexEdge> getEdges() {
        return Collections.unmodifiableMap(edges);
    }
}