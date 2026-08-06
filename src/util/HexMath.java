package util;

import model.map.hex.Point;

public final class HexMath {
    public static final double SQRT_3 = Math.sqrt(3);

    private HexMath() {}

    public static Point pixelToHex(double mouseX, double mouseY, double centerX, double centerY, double hexSize) {
        double dx = (mouseX - centerX) / hexSize;
        double dy = (mouseY - centerY) / hexSize;

        double qFrac = SQRT_3 / 3.0 * dx - 1.0 / 3.0 * dy;
        double rFrac = 2.0 / 3.0 * dy;

        return axialRound(qFrac, rFrac);
    }

    private static Point axialRound(double qFrac, double rFrac) {
        double sFrac = -qFrac - rFrac;
        int q = (int) Math.round(qFrac);
        int r = (int) Math.round(rFrac);
        int s = (int) Math.round(sFrac);

        if (q + r + s == 0) return new Point(q, r);

        double qDiff = Math.abs(qFrac - q);
        double rDiff = Math.abs(rFrac - r);
        double sDiff = Math.abs(sFrac - s);

        if (qDiff >= rDiff) {
            if (qDiff >= sDiff) q = -r - s;
        } else {
            if (rDiff >= sDiff) r = -q - s;
        }
        return new Point(q, r);
    }
}