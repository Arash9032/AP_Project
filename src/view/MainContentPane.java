package view;

import controller.GameEngine;
import view.panel.GamePanel;
import view.panel.SideMenuPanel;
import view.panel.StaticPanelType;

import javax.swing.*;
import java.awt.*;

public class MainContentPane extends JPanel {
    private static final String GAME_CONTAINER_KEY = "ACTIVE_GAME";

    private final CardLayout layout;
    private final GameEngine engine;
    private final GameContainer gameContainer;

    public MainContentPane(GameEngine engine) {
        this.engine = engine;
        layout = new CardLayout();
        setLayout(layout);
        addStaticPanels();
        gameContainer = new GameContainer(new GamePanel(engine.getGameState()) , new SideMenuPanel());
        add(gameContainer , GAME_CONTAINER_KEY);
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
        gameContainer.getGamePanel().setGameState(engine.getGameState());
        layout.show(this , GAME_CONTAINER_KEY);
    }

    @Override
    public CardLayout getLayout() {
        return layout;
    }

    public GameEngine getEngine() {
        return engine;
    }

    public GameContainer getGameContainer() {
        return gameContainer;
    }
}
