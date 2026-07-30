package model.map.hex;

import java.util.Objects;

public class Point {
    private final int q;
    private final int r;

    public Point(int q, int r) {
        this.q = q;
        this.r = r;
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return q == point.q && r == point.r;
    }

    @Override
    public int hashCode() {
        return Objects.hash(q, r);
    }
}