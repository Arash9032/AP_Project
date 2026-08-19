package view.render;

import config.Constants;
import view.camera.Camera;

public class RenderContext {

    private boolean resourceOverlayActive;

    public boolean isDetailed(Camera camera) {
        if (camera == null) return false;
        return (camera.getHexSize() >= Constants.MINIMUM_DETAILED_SIZE) && !resourceOverlayActive;
    }

    public boolean isResourceOverlayActive() {
        return resourceOverlayActive;
    }

    public void setResourceOverlayActive(boolean resourceOverlayActive) {
        this.resourceOverlayActive = resourceOverlayActive;
    }
}