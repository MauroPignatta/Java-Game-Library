package com.mpJavaGame.gfx.background;

import com.mpJavaGame.game.Renderer;

abstract class Background {

    protected int width, height;

    public Background(int width, int height) {
        this.width = width;
        this.height = height;
    }

    protected abstract void render(Renderer g);

}
