package com.mpJavaGame.animation;

import com.mpJavaGame.gfx.Texture;

public class BackForthAnimation extends Animation {

    private boolean forth = true;

    public BackForthAnimation(float seconds, Texture[] sprites) {
        super(seconds, sprites);
    }

    @Override
    public void update(float deltaT) {
        timer += deltaT;

        if (timer >= animSpeed) {
            index += forth ? 1 : -1;
            timer = 0;

            if (index == sprites.length - 1 || index == 0)
                changeDirection();
        }
    }

    private void changeDirection() {
        if (forth)
            index = sprites.length - 1;
        else
            index = 0;
        forth = !forth;
    }
}
