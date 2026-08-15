package view.panel;


import model.map.hex.Hex;
import model.unit.Unit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class SideMenuPanel extends JPanel {

    private static final int PANEL_WIDTH = 260;

    private final JLabel coordinateLabel;
    private final JLabel terrainLabel;
    private final JLabel isWithinBorder;

    private final DefaultListModel<String> unitListModel;
    private final JList<String> unitList;
    private List<Unit> currentUnits;

    private final JPanel actionPanel;
    private final JLabel selectedUnitLabel;
    private final JButton moveButton;

    public SideMenuPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, 0));
        setLayout(new BorderLayout(0, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel hexInfoPanel = createHexInfoPanel();
        add(hexInfoPanel, BorderLayout.NORTH);

        coordinateLabel = new JLabel("Coordinates: -");
        terrainLabel = new JLabel("Terrain: -");
        isWithinBorder = new JLabel("Is Within Border: -");

        hexInfoPanel.add(coordinateLabel);
        hexInfoPanel.add(terrainLabel);
        hexInfoPanel.add(isWithinBorder);

        unitListModel = new DefaultListModel<>();
        unitList = new JList<>(unitListModel);
        unitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        unitList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                handleUnitSelection(unitList.getSelectedIndex());
            }
        });

        JScrollPane unitScrollPane = new JScrollPane(unitList);
        unitScrollPane.setBorder(BorderFactory.createTitledBorder("Units in Hex"));
        add(unitScrollPane, BorderLayout.CENTER);

        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Unit Actions"));

        selectedUnitLabel = new JLabel("No unit selected");
        selectedUnitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        moveButton = new JButton("Move Unit");
        moveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        moveButton.setEnabled(false);

        actionPanel.add(selectedUnitLabel);
        actionPanel.add(Box.createVerticalStrut(10));
        actionPanel.add(moveButton);

        add(actionPanel, BorderLayout.SOUTH);
    }

    private JPanel createHexInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Hex Info"));
        return panel;
    }

    public void updateSelectedHex(Hex hex, List<Unit> units) {
        if (hex == null) {
            clearPanel();
            return;
        }

        coordinateLabel.setText("Coordinates: (" + hex.getCoordinate().getQ() + ", " + hex.getCoordinate().getR() + ")");
        terrainLabel.setText("Terrain: " + hex.getTerrain().name());
        isWithinBorder.setText("Is Within Border: " + (hex.isWithinBorder() ? "YES" : "NO"));

        this.currentUnits = units;
        unitListModel.clear();

        for (Unit unit : units) {
            unitListModel.addElement(unit.getUnitType().name() + " [HP: " + unit.getHp() + "/" + unit.getMaximumHp() + "]");
        }

        resetUnitSelection();
    }

    private void handleUnitSelection(int index) {
        if (index < 0 || currentUnits == null || index >= currentUnits.size()) {
            resetUnitSelection();
            return;
        }

        Unit selectedUnit = currentUnits.get(index);
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
        currentUnits = null;
        resetUnitSelection();
    }

    public JButton getMoveButton() {
        return moveButton;
    }
}