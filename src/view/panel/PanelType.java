package view.panel;

import view.MainContentPane;

import javax.swing.*;

public enum PanelType {
    MENU {
        @Override
        public JPanel createPage(MainContentPane mainContentPane) {
            return new MenuPanel(mainContentPane);
        }
    },
    SETTINGS {
        @Override
        public JPanel createPage(MainContentPane mainContentPane) {
            return new SettingsPanel(mainContentPane);
        }
    },
    GAME {
        @Override
        public JPanel createPage(MainContentPane mainContentPane) {
            return new GamePanel(mainContentPane);
        }
    };

    public abstract JPanel createPage(MainContentPane mainContentPane);
}
