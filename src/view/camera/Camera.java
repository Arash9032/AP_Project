package view.camera;

import config.Constants;

public class Camera {
    public static final double MAX_HEX_SIZE = 1.5 * Constants.FRAME_HEIGHT / Constants.MAP_RADIUS;
    public static final double MIN_HEX_SIZE = MAX_HEX_SIZE / 3.0;

    private double x;
    private double y;
    private double hexSize;

    public Camera(double initialHexSize) {
        this.x = 0;
        this.y = 0;
        this.hexSize = initialHexSize;
    }

    public void applyZoom(double delta, double mouseX, double mouseY, int screenWidth, int screenHeight) {
        double oldHexSize = this.hexSize;
        this.hexSize += delta;

        if (this.hexSize > MAX_HEX_SIZE) this.hexSize = MAX_HEX_SIZE;
        else if (this.hexSize < MIN_HEX_SIZE) this.hexSize = MIN_HEX_SIZE;

        if (this.hexSize == oldHexSize) return;

        double scale = this.hexSize / oldHexSize;

        double centerX = getCenterX(screenWidth);
        double centerY = getCenterY(screenHeight);

        double dx = mouseX - centerX;
        double dy = mouseY - centerY;

        this.x -= dx * (1.0 - scale);
        this.y -= dy * (1.0 - scale);
    }

    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public double getCenterX(int screenWidth) {
        return (screenWidth / 2.0) - this.x;
    }

    public double getCenterY(int screenHeight) {
        return (screenHeight / 2.0) - this.y;
    }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public double getHexSize() { return hexSize; }
    public void setHexSize(double hexSize) { this.hexSize = hexSize; }
}