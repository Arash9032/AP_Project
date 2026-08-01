package view;

import view.panel.GamePanel;
import view.panel.StaticPanelType;

import javax.swing.*;
import java.awt.*;

public class MainContentPane extends JPanel {
    private static final String GAME_PANEL_KEY = "ACTIVE_GAME";

    private final CardLayout layout;
    public MainContentPane() {
        layout = new CardLayout();
        setLayout(layout);
        addStaticPanels();
    }

    private void addStaticPanels(){
        for(StaticPanelType staticPanelType : StaticPanelType.values()){
            add(staticPanelType.createPanel(this) , staticPanelType.name());
        }
    }

    public void showStaticPanel(StaticPanelType staticPanelType){
        layout.show(this , staticPanelType.name());
    }

    public void startNewGame(){
        GamePanel gamePanel = new GamePanel(this);
        add(gamePanel , GAME_PANEL_KEY);
        layout.show(this , GAME_PANEL_KEY);
    }
}
