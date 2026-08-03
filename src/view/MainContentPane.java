package view;

import controller.GameEngine;
import model.GameState;
import view.panel.GamePanel;
import view.panel.MenuPanel;
import view.panel.SettingsPanel;
import view.panel.StaticPanelType;

import javax.swing.*;
import java.awt.*;

public class MainContentPane extends JPanel {
    private static final String GAME_PANEL_KEY = "ACTIVE_GAME";

    private final CardLayout layout;
    private final GameEngine engine;
    private GamePanel gamePanel;
    public MainContentPane(GameEngine engine) {
        this.engine = engine;
        layout = new CardLayout();
        setLayout(layout);
        addStaticPanels();
        gamePanel = new GamePanel(this , engine.getGameState());
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
        setGamePanel(new GamePanel(this , engine.getGameState()));
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

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.gamePanel.repaint();
        this.gamePanel.revalidate();
    }
}
