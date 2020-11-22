package com.mpJavaGame.gfx;

import com.mpJavaGame.animation.Animation;
import com.mpJavaGame.animation.SequenceAnimation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MovingAnimatedBackground extends MovingBackground {

    private Animation animation;

    public MovingAnimatedBackground(BufferedImage images[], int delayMS, int width, int height, float sx, float sy) {
        super(images[0], width, height, sx, sy);
        animation = new SequenceAnimation(delayMS, images);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        animation.update();
        setImage(animation.getCurrentSprite());
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }
}
