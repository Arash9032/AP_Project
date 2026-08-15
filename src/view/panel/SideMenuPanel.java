package view.panel;

import model.map.hex.Hex;
import model.unit.Unit;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SideMenuPanel extends JPanel {

    private static final int PANEL_WIDTH = 260;
    private static final Font TITLE_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 17);
    private static final Font BODY_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
    private static final Font BUTTON_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 16);

    private static final Color PANEL_BG_COLOR = new Color(90, 0, 174);
    private static final Color LIST_BG_COLOR = new Color(55, 0, 105);
    private static final Color BUTTON_BG_COLOR = new Color(112, 0, 112);
    private static final Color ACCENT_BORDER_COLOR = new Color(255, 0, 221);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);

    private final JLabel coordinateLabel;
    private final JLabel terrainLabel;
    private final JLabel isWithinBorder;

    private final DefaultListModel<Unit> unitListModel;
    private final JList<Unit> unitList;

    private final JPanel actionPanel;
    private final JLabel selectedUnitLabel;
    private final JButton moveButton;

    public SideMenuPanel() {
        setupPanelLayout();

        coordinateLabel = createLabel("Coordinates: -", BODY_FONT);
        terrainLabel = createLabel("Terrain: -", BODY_FONT);
        isWithinBorder = createLabel("Is Within Border: -", BODY_FONT);
        add(createHexInfoSection(), BorderLayout.NORTH);

        unitListModel = new DefaultListModel<>();
        unitList = createUnitList();
        add(createUnitListSection(), BorderLayout.CENTER);

        selectedUnitLabel = createLabel("No unit selected", TITLE_FONT);
        moveButton = createMoveButton();
        actionPanel = createActionSection();
        add(actionPanel, BorderLayout.SOUTH);
    }

    private void setupPanelLayout() {
        setPreferredSize(new Dimension(PANEL_WIDTH, 0));
        setLayout(new BorderLayout(0, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(PANEL_BG_COLOR);
    }

    private JPanel createHexInfoSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(createTitledBorder("Hex Info"));
        panel.setBackground(PANEL_BG_COLOR);

        panel.add(coordinateLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(terrainLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(isWithinBorder);

        return panel;
    }

    private JList<Unit> createUnitList() {
        JList<Unit> list = new JList<>(unitListModel);
        list.setFont(BODY_FONT);
        list.setBackground(LIST_BG_COLOR);
        list.setForeground(TEXT_COLOR);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new UnitListCellRenderer(LIST_BG_COLOR, BUTTON_BG_COLOR, TEXT_COLOR));

        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                handleUnitSelection(list.getSelectedValue());
            }
        });
        return list;
    }

    private JScrollPane createUnitListSection() {
        JScrollPane scrollPane = new JScrollPane(unitList);
        scrollPane.setBorder(createTitledBorder("Units in Hex"));
        scrollPane.setBackground(PANEL_BG_COLOR);
        scrollPane.getViewport().setBackground(LIST_BG_COLOR);
        return scrollPane;
    }

    private JButton createMoveButton() {
        JButton button = new JButton("Move Unit");
        button.setFont(BUTTON_FONT);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setEnabled(false);

        button.setBackground(BUTTON_BG_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_BORDER_COLOR, 1),
                new EmptyBorder(8, 15, 8, 15)
        ));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(button.isEnabled()){
                    button.setBackground(ACCENT_BORDER_COLOR);
                    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_BG_COLOR);
                button.setCursor(Cursor.getDefaultCursor());
            }
        });
        return button;
    }

    private JPanel createActionSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(createTitledBorder("Unit Actions"));
        panel.setBackground(PANEL_BG_COLOR);

        selectedUnitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(selectedUnitLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(moveButton);
        panel.add(Box.createVerticalStrut(5));

        return panel;
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(TEXT_COLOR);
        return label;
    }

    private TitledBorder createTitledBorder(String title) {
        Border lineBorder = BorderFactory.createLineBorder(ACCENT_BORDER_COLOR, 1);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, title);
        titledBorder.setTitleFont(TITLE_FONT);
        titledBorder.setTitleColor(TEXT_COLOR);
        return titledBorder;
    }

    public void updateSelectedHex(Hex hex, List<Unit> units) {
        if (hex == null) {
            clearPanel();
            return;
        }

        coordinateLabel.setText("Coordinates: (" + hex.getCoordinate().getQ() + ", " + hex.getCoordinate().getR() + ")");
        terrainLabel.setText("Terrain: " + hex.getTerrain().name());
        isWithinBorder.setText("Is Within Border: " + (hex.isWithinBorder() ? "YES" : "NO"));

        unitListModel.clear();
        for (Unit unit : units) {
            unitListModel.addElement(unit);
        }

        resetUnitSelection();
    }

    private void handleUnitSelection(Unit selectedUnit) {
        if (selectedUnit == null) {
            resetUnitSelection();
            return;
        }

        selectedUnitLabel.setText(selectedUnit.getUnitType().name() + " Selected");
        moveButton.setEnabled(true);
    }

    private void resetUnitSelection() {
        unitList.clearSelection();
        selectedUnitLabel.setText("No unit selected");
        moveButton.setEnabled(false);
    }

    public void clearPanel() {
        coordinateLabel.setText("Coordinates: -");
        terrainLabel.setText("Terrain: -");
        isWithinBorder.setText("Is Within Border: -");
        unitListModel.clear();
        resetUnitSelection();
    }

    public JButton getMoveButton() {
        return moveButton;
    }
}