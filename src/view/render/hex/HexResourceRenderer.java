package view.render.hex;

import model.map.hex.HexResource;
import util.IOHandler;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

public enum HexResourceRenderer {

    WOOD(HexResource.WOOD, "/resources/icons/hexresource/wood.png") {
        private final Rectangle2D.Double log = new Rectangle2D.Double();
        private final Color mainColor = new Color(139, 69, 19);

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            double width = size * 0.8;
            double height = size * 0.4;
            log.setRect(-width / 2.0, -height / 2.0, width, height);

            g2.setColor(mainColor);
            g2.fill(log);
            g2.setColor(Color.BLACK);
            g2.draw(log);
        }
    },
    STONE(HexResource.STONE, "/resources/icons/hexresource/stone.png") {
        private final Path2D.Double rock = new Path2D.Double();

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            rock.reset();
            double s = size / 2.0;
            rock.moveTo(-s * 0.6, -s * 0.8);
            rock.lineTo(s * 0.4, -s * 0.9);
            rock.lineTo(s * 0.8, s * 0.2);
            rock.lineTo(s * 0.2, s * 0.8);
            rock.lineTo(-s * 0.8, s * 0.6);
            rock.closePath();

            g2.setColor(Color.GRAY);
            g2.fill(rock);
            g2.setColor(Color.DARK_GRAY);
            g2.draw(rock);
        }
    },
    IRON(HexResource.IRON, "/resources/icons/hexresource/iron.png") {
        private final Path2D.Double ingot = new Path2D.Double();
        private final Color mainColor = new Color(169, 169, 169);

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            ingot.reset();
            double w = size * 0.4;
            double h = size * 0.25;
            ingot.moveTo(-w, -h);
            ingot.lineTo(w, -h);
            ingot.lineTo(w * 1.5, h);
            ingot.lineTo(-w * 1.5, h);
            ingot.closePath();

            g2.setColor(mainColor);
            g2.fill(ingot);
            g2.setColor(Color.BLACK);
            g2.draw(ingot);
        }
    },
    CROPS(HexResource.CROPS, "/resources/icons/hexresource/crops.png") {
        private final Path2D.Double sheaf = new Path2D.Double();
        private final Color mainColor = new Color(255, 215, 0);

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            sheaf.reset();
            double s = size / 2.0;
            sheaf.moveTo(0, -s);
            sheaf.lineTo(s * 0.5, 0);
            sheaf.lineTo(0, s);
            sheaf.lineTo(-s * 0.5, 0);
            sheaf.closePath();

            g2.setColor(mainColor);
            g2.fill(sheaf);
            g2.setColor(Color.ORANGE);
            g2.draw(sheaf);
        }
    },
    LIVESTOCK(HexResource.LIVESTOCK, "/resources/icons/hexresource/livestock.png") {
        private final Ellipse2D.Double body = new Ellipse2D.Double();
        private final Color mainColor = new Color(255, 182, 193);

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            double w = size * 0.7;
            double h = size * 0.5;
            body.setFrame(-w / 2.0, -h / 2.0, w, h);

            g2.setColor(mainColor);
            g2.fill(body);
            g2.setColor(Color.BLACK);
            g2.draw(body);
        }
    },
    FISH(HexResource.FISH, "/resources/icons/hexresource/fish.png") {
        private final Path2D.Double fishShape = new Path2D.Double();
        private final Color mainColor = new Color(64, 224, 208);

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            fishShape.reset();
            double s = size / 2.0;
            fishShape.moveTo(s * 0.8, 0);
            fishShape.curveTo(s * 0.4, -s * 0.6, -s * 0.4, -s * 0.6, -s * 0.6, 0);
            fishShape.lineTo(-s, -s * 0.4);
            fishShape.lineTo(-s * 0.8, 0);
            fishShape.lineTo(-s, s * 0.4);
            fishShape.lineTo(-s * 0.6, 0);
            fishShape.curveTo(-s * 0.4, s * 0.6, s * 0.4, s * 0.6, s * 0.8, 0);
            fishShape.closePath();

            g2.setColor(mainColor);
            g2.fill(fishShape);
            g2.setColor(Color.BLUE);
            g2.draw(fishShape);
        }
    },
    HORSE(HexResource.HORSE, "/resources/icons/hexresource/horse.png") {
        private final Path2D.Double horseHead = new Path2D.Double();
        private final Color mainColor = new Color(139, 69, 19);

        @Override
        protected void drawShape(Graphics2D g2, double size) {
            horseHead.reset();
            double s = size / 2.0;
            horseHead.moveTo(-s * 0.4, s * 0.8);
            horseHead.lineTo(-s * 0.2, -s * 0.2);
            horseHead.lineTo(s * 0.2, -s * 0.8);
            horseHead.lineTo(s * 0.6, -s * 0.4);
            horseHead.lineTo(s * 0.8, 0);
            horseHead.lineTo(s * 0.4, s * 0.4);
            horseHead.lineTo(s * 0.2, s * 0.8);
            horseHead.closePath();

            g2.setColor(mainColor);
            g2.fill(horseHead);
            g2.setColor(Color.BLACK);
            g2.draw(horseHead);
        }
    },
    NONE(HexResource.NONE, null) {
        @Override
        protected void drawShape(Graphics2D g2, double size) {
        }
    };

    private static final Map<HexResource, HexResourceRenderer> MAP = new EnumMap<>(HexResource.class);

    static {
        for (HexResourceRenderer e : HexResourceRenderer.values()) {
            MAP.put(e.getResource(), e);
        }
    }

    private final HexResource resource;
    private BufferedImage icon;

    HexResourceRenderer(HexResource resource, String iconFilePath) {
        this.resource = resource;
        if (iconFilePath != null) {
            try {
                this.icon = IOHandler.loadImage(iconFilePath);
            } catch (Exception e) {
                this.icon = null;
            }
        }
    }

    public HexResource getResource() {
        return resource;
    }

    public static HexResourceRenderer getFromResource(HexResource resource) {
        HexResourceRenderer renderer = MAP.get(resource);
        if (renderer != null) {
            return renderer;
        }
        throw new IllegalStateException("HexResource " + resource.name() + " doesn't have a HexResourceRenderer.");
    }

    public void drawResourceIcon(Graphics2D g2, double size) {
        if (this == NONE) return;

        if (icon != null) {
            int iconSize = (int) size;
            int centerOffset = -iconSize / 2;
            g2.drawImage(icon, centerOffset, centerOffset, iconSize, iconSize, null);
        } else {
            drawShape(g2, size);
        }
    }

    protected abstract void drawShape(Graphics2D g2, double size);

    public BufferedImage getIcon() {
        return icon;
    }
}