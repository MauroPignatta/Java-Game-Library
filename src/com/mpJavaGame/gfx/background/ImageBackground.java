package com.mpJavaGame.gfx.background;

import com.mpJavaGame.game.Renderer;
import com.mpJavaGame.gfx.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageBackground extends Background {

    private Texture background;

    public ImageBackground(Texture image, int width, int height) {
        super(width, height);
        background = image;
    }

    public void setImage(Texture image) {
        background = image;
    }

    @Override
    public void render(Renderer r) {
        r.draw(background, 0, 0);
    }

}
