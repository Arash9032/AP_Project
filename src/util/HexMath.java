package util;

import config.Constants;
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

    public static boolean isHexInSight(double cx, double cy, double hexSize, int screenWidth, int screenHeight) {
        return !(cx + hexSize < 0 || cx - hexSize > screenWidth ||
                cy + hexSize < 0 || cy - hexSize > screenHeight);
    }

    public static double getWorldX(int q, int r) {
        return Constants.BASE_HEX_SIZE * HexMath.SQRT_3 * (q + r / 2.0);
    }

    public static double getWorldY(int r) {
        return Constants.BASE_HEX_SIZE * 3.0 / 2.0 * r;
    }
}