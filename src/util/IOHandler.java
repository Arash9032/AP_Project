package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class IOHandler {

    private IOHandler() {}

    public static BufferedImage loadImage(String path) {
        try (InputStream is = IOHandler.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new RuntimeException("Image not found in classpath: " + path);
            }
            return ImageIO.read(new BufferedInputStream(is));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image data: " + path, e);
        }
    }
}