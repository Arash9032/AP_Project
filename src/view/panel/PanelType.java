package view.panel;

import javax.swing.*;

public enum PanelType {
    MENU {
        @Override
        public JPanel createPage() {
            return new MenuPanel();
        }
    },
    SETTINGS {
        @Override
        public JPanel createPage() {
            return new SettingsPanel();
        }
    },
    GAME {
        @Override
        public JPanel createPage() {
            return new GamePanel();
        }
    };

    public abstract JPanel createPage();
}
