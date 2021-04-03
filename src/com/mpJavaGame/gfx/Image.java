package com.mpJavaGame.gfx;

import java.awt.image.BufferedImage;

public abstract class Image {

    private int width, height;
    private int[] pixels;

    public Image(BufferedImage image){
        width = image.getWidth();
        height = image.getHeight();
        pixels = image.getRGB(0, 0, width, height, null, 0, width);
        image.flush();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() { return pixels; }

}
