package controller.uiux;

import model.GameState;
import model.unit.Unit;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectUnitListener implements ListSelectionListener{
    private final GameState gameState;

    public SelectUnitListener(GameState gameState) {
        this.gameState = gameState;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void valueChanged(ListSelectionEvent e) {
        gameState.setSelectedUnit(((JList<Unit>) e.getSource()).getSelectedValue());
    }
}
