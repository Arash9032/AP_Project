package view.panel;

import view.MainContentPane;

import javax.swing.*;

public enum StaticPanelType {
    MENU {
        @Override
        public JPanel createPanel(MainContentPane mainContentPane) {
            return new MenuPanel(mainContentPane);
        }
    },
    SETTINGS {
        @Override
        public JPanel createPanel(MainContentPane mainContentPane) {
            return new SettingsPanel(mainContentPane);
        }
    };

    public abstract JPanel createPanel(MainContentPane mainContentPane);
}
