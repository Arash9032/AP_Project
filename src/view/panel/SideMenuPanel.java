package view.panel;

import model.map.hex.Hex;
import model.unit.Unit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class SideMenuPanel extends JPanel {

    private static final int PANEL_WIDTH = 260;
    private static final Font TITLE_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 17);
    private static final Font BODY_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
    private static final Font BUTTON_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 16);

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
    }

    private JPanel createHexInfoSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(createTitledBorder("Hex Info"));

        panel.add(coordinateLabel);
        panel.add(terrainLabel);
        panel.add(isWithinBorder);

        return panel;
    }

    private JList<String> createUnitList() {
        JList<String> list = new JList<>(unitListModel);
        list.setFont(BODY_FONT);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                handleUnitSelection(list.getSelectedIndex());
            }
        });
        return list;
    }

    private JScrollPane createUnitListSection() {
        JScrollPane scrollPane = new JScrollPane(unitList);
        scrollPane.setBorder(createTitledBorder("Units in Hex"));
        return scrollPane;
    }

    private JButton createMoveButton() {
        JButton button = new JButton("Move Unit");
        button.setFont(BUTTON_FONT);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setEnabled(false);
        return button;
    }

    private JPanel createActionSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(createTitledBorder("Unit Actions"));

        selectedUnitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(selectedUnitLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(moveButton);

        return panel;
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private TitledBorder createTitledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleFont(TITLE_FONT);
        return border;
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