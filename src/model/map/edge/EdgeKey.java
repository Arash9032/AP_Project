package model.map.edge;

import model.map.hex.Point;

import java.util.Objects;

public class EdgeKey {
    private final Point p1;
    private final Point p2;

    public EdgeKey(Point a, Point b) {
        if(a.getQ() < b.getQ() || (a.getQ() == b.getQ() && a.getR() < b.getR())){
            p1 = a;
            p2 = b;
        }
        else{
            p1 = b;
            p2 = a;
        }
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1 , p2);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        EdgeKey o = (EdgeKey) obj;
        return p1.equals(o.p1) && p2.equals(o.p2);
    }
}
