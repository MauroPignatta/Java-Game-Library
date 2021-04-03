package com.mpJavaGame.animation;

import com.mpJavaGame.gfx.Texture;

import java.awt.image.BufferedImage;

public abstract class Animation {

    protected int animSpeed;
    protected int index;
    protected long lastTime, timer;
    protected Texture[] sprites;

    public Animation(int animSpeedMilliseconds, Texture[] sprites) {
        this.animSpeed = animSpeedMilliseconds;
        this.sprites = sprites;
        lastTime = System.currentTimeMillis();
    }

    public Texture getCurrentSprite() {
        return sprites[index];
    }

    public void setSprites(Texture[] sprites) {
        if (this.sprites == null || !this.sprites.equals(sprites)) {
            this.sprites = sprites;
            reset();
        }
    }

    public void reset() {
        index = 0;
        lastTime = 0;
        timer = 0;
    }

    public abstract void update();

}
