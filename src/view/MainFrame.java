package view;

import config.Constants;
import controller.GameEngine;

import javax.swing.*;

public class MainFrame extends JFrame {
    private MainContentPane mainContentPane;
    public MainFrame(GameEngine engine) {
        super("Hex Land: Rise of Tribes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setLocationRelativeTo(null);
        mainContentPane = new MainContentPane(engine);
        setContentPane(mainContentPane);
        setVisible(true);
    }

    public MainContentPane getMainContentPane() {
        return mainContentPane;
    }

    public void setMainContentPane(MainContentPane mainContentPane) {
        this.mainContentPane = mainContentPane;
    }
}
