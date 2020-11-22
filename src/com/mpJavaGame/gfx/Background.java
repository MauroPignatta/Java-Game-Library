package com.mpJavaGame.gfx;

import java.awt.*;

abstract class Background {

    protected int width, height;

    public Background(int width, int height) {
        this.width = width;
        this.height = height;
    }

    protected abstract void render(Graphics2D g);

}
