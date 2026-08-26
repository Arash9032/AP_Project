package controller.uiux;

import view.render.RenderContext;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ResourceOverlayListener extends KeyAdapter {
    private RenderContext renderContext;

    public ResourceOverlayListener(RenderContext renderContext) {
        this.renderContext = renderContext;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_TAB) renderContext.setResourceOverlayActive(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_TAB) renderContext.setResourceOverlayActive(false);
    }
}
