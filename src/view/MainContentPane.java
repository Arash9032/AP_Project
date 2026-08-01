package view;

import view.panel.PanelType;

import javax.swing.*;
import java.awt.*;

public class MainContentPane extends JPanel {
    private final CardLayout layout;
    public MainContentPane() {
        layout = new CardLayout();
        setLayout(layout);
        addPanels();
    }

    private void addPanels(){
        for(PanelType panelType : PanelType.values()){
            add(panelType.createPage(this) , panelType.name());
        }
    }

    public void showPanel(PanelType panelType){
        layout.show(this , panelType.name());
    }
}
