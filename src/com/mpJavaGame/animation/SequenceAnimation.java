package com.mpJavaGame.animation;

import java.awt.image.BufferedImage;

public class SequenceAnimation extends Animation {

    public SequenceAnimation(int animSpeedMilliseconds) {
        super(animSpeedMilliseconds, null);
    }

    public SequenceAnimation(int animSpeedMilliseconds, BufferedImage[] sprites) {
        super(animSpeedMilliseconds, sprites);
    }

    @Override
    public void update() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer >= animSpeed) {
            index++;
            timer = 0;
            index = index >= sprites.length ? 0 : index;
        }
    }
}
