package com.mpJavaGame.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageBackground extends Background {

    private BufferedImage background;

    public ImageBackground(BufferedImage image, int width, int height) {
        super(width, height);
        background = image;
    }

    public void setImage(BufferedImage image) {
        background = image;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(background, 0, 0, width, height, null);
    }

}
