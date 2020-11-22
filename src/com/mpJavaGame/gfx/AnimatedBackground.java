package com.mpJavaGame.gfx;

import com.mpJavaGame.animation.Animation;
import com.mpJavaGame.animation.SequenceAnimation;
import com.mpJavaGame.interfaces.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedBackground extends Background implements Renderable {

    private Animation animation;

    public AnimatedBackground(BufferedImage images[], int delayMS, int width, int height) {
        super(width, height);
        animation = new SequenceAnimation(delayMS, images);
    }

    @Override
    public void update(float delta) {
        animation.update();
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(animation.getCurrentSprite(), 0, 0, width, height, null);
    }
}
