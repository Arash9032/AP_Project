package view.panel;

import config.Constants;
import view.MainContentPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPanel extends JPanel {

    private final MainContentPane mainContentPane;

    public MenuPanel(MainContentPane mainContentPane) {
        this.mainContentPane = mainContentPane;
        setLayout(new GridBagLayout());
        setBackground(Constants.GAME_BACKGROUND_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        addGameTitle(gbc);
        addButtons(gbc);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(250, 60));
        button.setFont(new Font("SansSerif", Font.BOLD, 24));
        button.setForeground(Constants.BUTTON_TEXT_COLOR);
        button.setBackground(Constants.BUTTON_BACKGROUND_COLOR);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);

        button.setBorder(BorderFactory.createLineBorder(Constants.BUTTON_BORDER_COLOR, 3));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Constants.BUTTON_BORDER_COLOR);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Constants.BUTTON_BACKGROUND_COLOR);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
    }

    private void addGameTitle(GridBagConstraints gbc){
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 50, 20);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Hex Land: Rise of Tribes");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        titleLabel.setForeground(Constants.BUTTON_TEXT_COLOR);
        add(titleLabel, gbc);
    }

    private void addButtons(GridBagConstraints gbc){
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy++;
        JButton startButton = createStyledButton("Start Game");
        startButton.addActionListener(e -> mainContentPane.startNewGame());
        add(startButton, gbc);

        gbc.gridy++;
        JButton loadButton = createStyledButton("Load Game");
        // TODO:
        add(loadButton, gbc);

        gbc.gridy++;
        JButton settingsButton = createStyledButton("Settings");
        settingsButton.addActionListener(e -> mainContentPane.showStaticPanel(StaticPanelType.SETTINGS));
        add(settingsButton, gbc);

        gbc.gridy++;
        JButton exitButton = createStyledButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton, gbc);
    }
}