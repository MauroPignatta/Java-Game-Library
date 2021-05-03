package com.mpJavaGame.animation;

import com.mpJavaGame.gfx.Texture;

public class SequenceAnimation extends Animation {

    public SequenceAnimation(float seconds, Texture[] sprites) {
        super(seconds, sprites);
    }

    @Override
    public void update(float deltaT) {
        timer += deltaT;

        if (timer >= animSpeed) {
            index++;
            timer = 0;
            index = index >= sprites.length ? 0 : index;
        }
    }
}
