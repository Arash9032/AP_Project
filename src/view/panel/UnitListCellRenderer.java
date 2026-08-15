package view.panel;

import model.unit.Unit;
import model.unit.UnitType;
import view.render.unit.UnitTypeRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

public class UnitListCellRenderer extends DefaultListCellRenderer {

    private static final int ICON_SIZE = 30;
    private static final Map<UnitType, ImageIcon> ICON_CACHE = new EnumMap<>(UnitType.class);

    private final Color defaultBackground;
    private final Color selectedBackground;
    private final Color textColor;

    public UnitListCellRenderer(Color defaultBackground, Color selectedBackground, Color textColor) {
        this.defaultBackground = defaultBackground;
        this.selectedBackground = selectedBackground;
        this.textColor = textColor;
        setBorder(new EmptyBorder(4, 6, 4, 6));
    }

    @Override
    public Component getListCellRendererComponent(
            JList<?> list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus
    ) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Unit) {
            Unit unit = (Unit) value;
            setText(unit.getUnitType().name() + " [HP: " + unit.getHp() + "/" + unit.getMaximumHp() + "]");
            setIcon(getScaledIcon(unit.getUnitType()));
        }

        if (isSelected) {
            setBackground(selectedBackground);
            setForeground(textColor);
        } else {
            setBackground(defaultBackground);
            setForeground(textColor);
        }

        return this;
    }

    private ImageIcon getScaledIcon(UnitType unitType) {
        ImageIcon cachedIcon = ICON_CACHE.get(unitType);
        if (cachedIcon != null) {
            return cachedIcon;
        }

        UnitTypeRenderer renderer = UnitTypeRenderer.getFromType(unitType);
        BufferedImage originalImage = renderer.getIcon();

        if (originalImage != null) {
            Image scaledImage = originalImage.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(scaledImage);
            ICON_CACHE.put(unitType, newIcon);
            return newIcon;
        }

        return null;
    }
}