package com.mpJavaGame.game;

import com.mpJavaGame.gfx.Image;

import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Renderer {

    private static final int ALPHA = 0xffff00ff;

    private int width, height;
    private int[] pixels;

    public Renderer(int width, int height, AbstractGame game) {
        this.width = width;
        this.height = height;
        this.pixels = ((DataBufferInt) game.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void draw(Image image, int offsetX, int offsetY) {
        if (isOffScreen(image, offsetX, offsetY)) return;

        int nx = 0;
        int ny = 0;
        int nw = image.getWidth();
        int nh = image.getHeight();

        if (offsetX < 0) nx -= offsetX;
        if (offsetY < 0) ny -= offsetY;
        if (nh + offsetY >= height) nh -= nh + offsetY - height;
        if (nw + offsetX >= width) nw -= nw + offsetX - width;

        for (int y = ny; y < nh; ++y) {
            for (int x = nx; x < nw; ++x) {
                setPixel(x + offsetX, y + offsetY, image.getPixels()[x + y * image.getWidth()]);
            }
        }
    }

    private void setPixel(int x, int y, int val) {
        if (x < 0 || x > width || y < 0 || y > height || val == ALPHA) return;

        pixels[x + y * width] = val;
    }

    private boolean isOffScreen(Image image, int offsetX, int offsetY) {
        boolean offScreen = offsetX < -image.getWidth();
        offScreen |= offsetY < -image.getHeight();
        offScreen |= offsetX >= width;
        offScreen |= offsetY >= height;
        return offScreen;
    }

    protected void clear() {
        Arrays.fill(pixels, 0);
    }

}
