package com.mpJavaGame.animation;

import com.mpJavaGame.gfx.Texture;

public abstract class Animation {

    protected float animSpeed;
    protected float timer;
    protected int index;
    protected Texture[] sprites;

    public Animation(float seconds, Texture[] sprites) {
        this.animSpeed = seconds;
        this.sprites = sprites;
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
        timer = 0;
    }

    public abstract void update(float deltaT);

}
