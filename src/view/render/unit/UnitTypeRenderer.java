package view.render.unit;

import model.unit.UnitType;
import util.IOHandler;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

public enum UnitTypeRenderer {

    WORKER(UnitType.WORKER, "/resources/icon/unit/worker.png") {
        private final Ellipse2D.Double workerShape = new Ellipse2D.Double();

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            double halfSize = size / 2.0;
            workerShape.setFrame(-halfSize, -halfSize, size, size);

            g2.setColor(Color.ORANGE);
            g2.fill(workerShape);
            g2.setColor(Color.DARK_GRAY);
            g2.draw(workerShape);
        }
    },
    BUILDER(UnitType.BUILDER, "/resources/icon/unit/builder.png") {
        private final Path2D.Double builderShape = new Path2D.Double();

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            builderShape.reset();
            double halfSize = size / 2.0;
            double thickness = size / 6.0;

            builderShape.moveTo(-thickness, -halfSize);
            builderShape.lineTo(thickness, -halfSize);
            builderShape.lineTo(thickness, -thickness);
            builderShape.lineTo(halfSize, -thickness);
            builderShape.lineTo(halfSize, thickness);
            builderShape.lineTo(thickness, thickness);
            builderShape.lineTo(thickness, halfSize);
            builderShape.lineTo(-thickness, halfSize);
            builderShape.lineTo(-thickness, thickness);
            builderShape.lineTo(-halfSize, thickness);
            builderShape.lineTo(-halfSize, -thickness);
            builderShape.lineTo(-thickness, -thickness);
            builderShape.closePath();

            g2.setColor(Color.YELLOW);
            g2.fill(builderShape);
            g2.setColor(Color.DARK_GRAY);
            g2.draw(builderShape);
        }
    },
    EXPLORER(UnitType.EXPLORER, "/resources/icon/unit/explorer.png") {
        private final Path2D.Double explorerTopShape = new Path2D.Double();
        private final Path2D.Double explorerBottomShape = new Path2D.Double();

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            explorerTopShape.reset();
            double halfSize = size / 2.0;
            double width = size / 3.0;

            explorerTopShape.moveTo(0, -halfSize);
            explorerTopShape.lineTo(width, 0);
            explorerTopShape.lineTo(-width, 0);
            explorerTopShape.closePath();

            explorerBottomShape.reset();
            explorerBottomShape.moveTo(0, halfSize);
            explorerBottomShape.lineTo(width, 0);
            explorerBottomShape.lineTo(-width, 0);
            explorerBottomShape.closePath();

            g2.setColor(Color.RED);
            g2.fill(explorerTopShape);
            g2.setColor(Color.WHITE);
            g2.fill(explorerBottomShape);

            g2.setColor(Color.BLACK);
            g2.draw(explorerTopShape);
            g2.draw(explorerBottomShape);
        }
    },
    BORDER_EXPANDER(UnitType.BORDER_EXPANDER, "/resources/icon/unit/border_expander.png") {
        private final Rectangle2D.Double flagPoleShape = new Rectangle2D.Double();
        private final Path2D.Double flagClothShape = new Path2D.Double();

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            double halfSize = size / 2.0;
            double quarterSize = size / 4.0;
            double poleWidth = size / 8.0;

            flagPoleShape.setRect(-quarterSize, -halfSize, poleWidth, size);
            g2.setColor(Color.BLACK);
            g2.fill(flagPoleShape);

            flagClothShape.reset();
            flagClothShape.moveTo(-quarterSize + poleWidth, -halfSize);
            flagClothShape.lineTo(halfSize, -quarterSize);
            flagClothShape.lineTo(-quarterSize + poleWidth, 0);
            flagClothShape.closePath();

            g2.setColor(Color.MAGENTA);
            g2.fill(flagClothShape);
            g2.setColor(Color.BLACK);
            g2.draw(flagClothShape);
        }
    };

    private static final Map<UnitType, UnitTypeRenderer> MAP = new EnumMap<>(UnitType.class);
    private static final BasicStroke THIN_STROKE = new BasicStroke(1.0f);
    private static final Color GLOW_CENTER_COLOR = new Color(255, 255, 0, 255);
    private static final Color GLOW_MIDDLE_COLOR = new Color(255, 255, 0, 150);
    private static final Color GLOW_EDGE_COLOR = new Color(255, 255, 0, 0);
    private static final float[] GLOW_FRACTIONS = {0.0f, 0.5f, 1.0f};
    private static final Color[] GLOW_COLORS = {GLOW_CENTER_COLOR, GLOW_MIDDLE_COLOR, GLOW_EDGE_COLOR};

    static {
        for (UnitTypeRenderer e : UnitTypeRenderer.values()) {
            MAP.put(e.getType(), e);
        }
    }

    private final UnitType type;
    private BufferedImage icon;

    private final Rectangle2D.Double hpBgRect = new Rectangle2D.Double();
    private final Rectangle2D.Double hpFgRect = new Rectangle2D.Double();

    UnitTypeRenderer(UnitType type, String iconFilePath) {
        this.type = type;
        try {
            this.icon = IOHandler.loadImage(iconFilePath);
        } catch (Exception e) {
            this.icon = null;
        }
    }

    public UnitType getType() {
        return type;
    }

    public static UnitTypeRenderer getFromType(UnitType type) {
        UnitTypeRenderer renderer = MAP.get(type);
        if (renderer != null) return renderer;
        throw new IllegalStateException("UnitType " + type.name() + " doesn't have a UnitTypeRenderer.");
    }

    public void drawUnit(Graphics2D g2, double size, int hp, int maxHp, boolean detailed, boolean isSelected) {
        if (isSelected && detailed) {
            int haloSize = (int) (size * 1.4);
            int haloOffset = -haloSize / 2;
            float radius = haloSize / 2f;
            RadialGradientPaint gradient = new RadialGradientPaint(0f , 0f , radius, GLOW_FRACTIONS, GLOW_COLORS);
            g2.setPaint(gradient);
            g2.fillOval(haloOffset, haloOffset, haloSize, haloSize);
        }
        if (icon != null) {
            int iconSize = (int) size;
            int centerOffset = -iconSize / 2;
            g2.drawImage(icon, centerOffset, centerOffset, iconSize, iconSize, null);
        } else {
            drawShape(g2, size);
        }

        if(detailed) drawHealthBar(g2, size / 1.5, hp, maxHp);
    }

    protected abstract void drawShape(Graphics2D g2, double size);

    private void drawHealthBar(Graphics2D g2, double size, int hp, int maxHp) {
        if (maxHp <= 0) return;

        double hpPercentage = Math.max(0, (double) hp / maxHp);

        double barHeight = size / 15.0;
        double barX = -size / 2.0;
        double barY = (size / 2.0) + 3.0;

        hpBgRect.setRect(barX, barY, size, barHeight);
        g2.setColor(Color.RED);
        g2.fill(hpBgRect);

        hpFgRect.setRect(barX, barY, size * hpPercentage, barHeight);
        g2.setColor(Color.GREEN);
        g2.fill(hpFgRect);

        g2.setColor(Color.BLACK);
        g2.setStroke(THIN_STROKE);
        g2.draw(hpBgRect);
    }

    public BufferedImage getIcon() {
        return icon;
    }
}