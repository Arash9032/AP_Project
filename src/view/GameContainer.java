package view;

import view.panel.GamePanel;
import view.panel.SideMenuPanel;

import javax.swing.*;
import java.awt.*;

public class GameContainer extends JPanel {
    private final GamePanel gamePanel;
    private final SideMenuPanel sideMenuPanel;

    public GameContainer(GamePanel gamePanel, SideMenuPanel sideMenuPanel) {
        super(new BorderLayout());
        this.gamePanel = gamePanel;
        this.sideMenuPanel = sideMenuPanel;
        add(gamePanel , BorderLayout.CENTER);
        add(sideMenuPanel, BorderLayout.EAST);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public SideMenuPanel getSideMenuPanel() {
        return sideMenuPanel;
    }
}
