package view.render.building;

import model.map.building.BuildingType;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.EnumMap;
import java.util.Map;

public enum BuildingTypeRenderer {

    LUMBER_MILL(BuildingType.LUMBER_MILL) {
        private final Rectangle2D.Double trunk = new Rectangle2D.Double();
        private final Ellipse2D.Double leaves = new Ellipse2D.Double();

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            trunk.setRect(-size / 4, -size / 4, size / 2, size / 2);
            g2.setColor(BROWN);
            g2.fill(trunk);

            leaves.setFrame(-size / 2, -size / 2, size, size * 0.6);
            g2.setColor(GREEN);
            g2.fill(leaves);
        }
    },
    FARM(BuildingType.FARM) {
        private final Path2D.Double diamond = new Path2D.Double();

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            diamond.reset();
            diamond.moveTo(0, -size / 2);
            diamond.lineTo(size / 2, 0);
            diamond.lineTo(0, size / 2);
            diamond.lineTo(-size / 2, 0);
            diamond.closePath();

            g2.setColor(WHEAT);
            g2.fill(diamond);
            g2.setColor(Color.DARK_GRAY);
            g2.draw(diamond);
        }
    },
    STONE_MINE(BuildingType.STONE_MINE) {
        private final Path2D.Double triangle = new Path2D.Double();

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            triangle.reset();
            triangle.moveTo(0, -size / 2);
            triangle.lineTo(size / 2, size / 2);
            triangle.lineTo(-size / 2, size / 2);
            triangle.closePath();

            g2.setColor(Color.GRAY);
            g2.fill(triangle);
            g2.setColor(Color.DARK_GRAY);
            g2.draw(triangle);
        }
    },
    IRON_MINE(BuildingType.IRON_MINE) {
        private final Path2D.Double triangle = new Path2D.Double();
        private final Ellipse2D.Double hole = new Ellipse2D.Double();

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            triangle.reset();
            triangle.moveTo(0, -size / 2);
            triangle.lineTo(size / 2, size / 2);
            triangle.lineTo(-size / 2, size / 2);
            triangle.closePath();

            g2.setColor(DARK_GRAY_CUSTOM);
            g2.fill(triangle);
            g2.setColor(Color.BLACK);
            g2.draw(triangle);

            hole.setFrame(-size / 6, 0, size / 3, size / 3);
            g2.setColor(Color.BLACK);
            g2.fill(hole);
        }
    },
    STABLE(BuildingType.STABLE) {
        private final Rectangle2D.Double rect = new Rectangle2D.Double();

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            rect.setRect(-size / 2, -size / 4, size, size / 2);
            g2.setColor(SIENNA);
            g2.fill(rect);
            g2.setColor(Color.DARK_GRAY);
            g2.setStroke(THICK_STROKE);
            g2.draw(rect);

            g2.drawLine((int)(-size / 2), (int)(-size / 4), (int)(size / 2), (int)(size / 4));
            g2.drawLine((int)(-size / 2), (int)(size / 4), (int)(size / 2), (int)(-size / 4));
        }
    },
    VILLAGE(BuildingType.VILLAGE) {
        private final Rectangle2D.Double base = new Rectangle2D.Double();
        private final Path2D.Double roof = new Path2D.Double();

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            base.setRect(-size / 4, -size / 8, size / 2, size * 0.375);
            g2.setColor(TAN);
            g2.fill(base);

            roof.reset();
            roof.moveTo(0, -size / 2);
            roof.lineTo(size / 3, -size / 8);
            roof.lineTo(-size / 3, -size / 8);
            roof.closePath();

            g2.setColor(FIREBRICK);
            g2.fill(roof);
        }
    },
    TOWN(BuildingType.TOWN) {
        private final Rectangle2D.Double base = new Rectangle2D.Double();
        private final Path2D.Double roof1 = new Path2D.Double();
        private final Path2D.Double roof2 = new Path2D.Double();

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            base.setRect(-size / 2, -size / 8, size, size * 0.375);
            g2.setColor(TAN);
            g2.fill(base);

            roof1.reset();
            roof1.moveTo(-size / 4, -size / 2);
            roof1.lineTo(0, -size / 8);
            roof1.lineTo(-size / 2, -size / 8);
            roof1.closePath();

            roof2.reset();
            roof2.moveTo(size / 4, -size / 2);
            roof2.lineTo(size / 2, -size / 8);
            roof2.lineTo(0, -size / 8);
            roof2.closePath();

            g2.setColor(FIREBRICK);
            g2.fill(roof1);
            g2.fill(roof2);
        }
    },
    TOWN_HALL(BuildingType.TOWN_HALL) {
        private final Rectangle2D.Double rect = new Rectangle2D.Double();

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            rect.setRect(-size / 2, -size / 2, size, size);
            g2.setColor(GOLD);
            g2.fill(rect);
            g2.setColor(Color.DARK_GRAY);
            g2.setStroke(THICK_STROKE);
            g2.draw(rect);
        }
    };

    private static final Map<BuildingType, BuildingTypeRenderer> MAP = new EnumMap<>(BuildingType.class);

    private static final BasicStroke THICK_STROKE = new BasicStroke(2.0f);
    private static final Color BROWN = new Color(139, 69, 19);
    private static final Color GREEN = new Color(34, 139, 34);
    private static final Color WHEAT = new Color(255, 165, 0);
    private static final Color DARK_GRAY_CUSTOM = new Color(105, 105, 105);
    private static final Color SIENNA = new Color(160, 82, 45);
    private static final Color TAN = new Color(210, 180, 140);
    private static final Color FIREBRICK = new Color(178, 34, 34);
    private static final Color GOLD = new Color(255, 215, 0);

    static {
        for (BuildingTypeRenderer e : BuildingTypeRenderer.values()) {
            MAP.put(e.getType(), e);
        }
    }

    private final BuildingType type;
    private final Rectangle2D.Double hpBgRect = new Rectangle2D.Double();
    private final Rectangle2D.Double hpFgRect = new Rectangle2D.Double();
    private static final BasicStroke THIN_STROKE = new BasicStroke(1.0f);

    BuildingTypeRenderer(BuildingType type) {
        this.type = type;
    }

    public BuildingType getType() {
        return type;
    }

    public static BuildingTypeRenderer getFromType(BuildingType type) {
        BuildingTypeRenderer renderer = MAP.get(type);
        if (renderer != null) return renderer;
        throw new IllegalStateException("BuildingType " + type.name() + " doesn't have a BuildingTypeRenderer.");
    }

    public void drawBuilding(Graphics2D g2, double size, int hp, int maxHp, boolean detailed) {
        drawShape(g2, size);
        if(detailed) drawHealthBar(g2, size, hp, maxHp);
    }

    protected abstract void drawShape(Graphics2D g2, double size);

    private void drawHealthBar(Graphics2D g2, double size, int hp, int maxHp) {
        if (maxHp <= 0) return;

        double hpPercentage = Math.max(0, (double) hp / maxHp);
        double barWidth = size * 1.2;
        double barHeight = size / 8.0;
        double barX = -barWidth / 2;
        double barY = size / 2 + 4;

        hpBgRect.setRect(barX, barY, barWidth, barHeight);
        g2.setColor(Color.RED);
        g2.fill(hpBgRect);

        hpFgRect.setRect(barX, barY, barWidth * hpPercentage, barHeight);
        g2.setColor(Color.GREEN);
        g2.fill(hpFgRect);

        g2.setColor(Color.BLACK);
        g2.setStroke(THIN_STROKE);
        g2.draw(hpBgRect);
    }
}