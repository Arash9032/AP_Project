package view;

import config.Constants;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Hex Land: Rise of Tribes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setContentPane(new MainContentPane());
        setVisible(true);
    }
}
