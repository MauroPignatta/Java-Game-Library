package com.mpJavaGame.animation;

import com.mpJavaGame.gfx.Texture;

public class BackForthAnimation extends Animation {

    private boolean forth = true;

    public BackForthAnimation(int animSpeedMilliseconds) {
        super(animSpeedMilliseconds, null);
    }

    public BackForthAnimation(int animSpeedMilliseconds, Texture[] sprites) {
        super(animSpeedMilliseconds, sprites);
    }

    @Override
    public void update() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer >= animSpeed) {
            if (index == sprites.length - 1 || index == 0)
                changeDirection();

            index += forth ? 1 : -1;
            timer = 0;
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
