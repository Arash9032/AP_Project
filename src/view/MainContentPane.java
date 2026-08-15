package view;

import controller.GameEngine;
import view.panel.GamePanel;
import view.panel.StaticPanelType;

import javax.swing.*;
import java.awt.*;

public class MainContentPane extends JPanel {
    private static final String GAME_PANEL_KEY = "ACTIVE_GAME";

    private final CardLayout layout;
    private final GameEngine engine;
    private final GamePanel gamePanel;
    public MainContentPane(GameEngine engine) {
        this.engine = engine;
        layout = new CardLayout();
        setLayout(layout);
        addStaticPanels();
        gamePanel = new GamePanel(engine.getGameState());
        add(gamePanel , GAME_PANEL_KEY);
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
        engine.startNewGame();
        gamePanel.setGameState(engine.getGameState());
        layout.show(this , GAME_PANEL_KEY);
    }

    @Override
    public CardLayout getLayout() {
        return layout;
    }

    public GameEngine getEngine() {
        return engine;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
