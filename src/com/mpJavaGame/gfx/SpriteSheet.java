package com.mpJavaGame.gfx;

import com.mpJavaGame.helper.ImageHelper;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage tileSet;
    private int tileWidth, tileHeight;

    private SpriteSheet(int tileWidth, int tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public SpriteSheet(String path, int tileWidth, int tileHeight) {
        this(tileWidth, tileHeight);
        this.tileSet = ImageHelper.loadImage(path);

    }

    public SpriteSheet(BufferedImage tileSet, int tileWidth, int tileHeight) {
        this(tileWidth, tileHeight);
        this.tileSet = tileSet;
    }

    public BufferedImage cropImage(int x, int y) {
        return tileSet.getSubimage(x, y, tileWidth, tileHeight);
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }
}
