package com.mpJavaGame.gfx.background;

import com.mpJavaGame.animation.Animation;
import com.mpJavaGame.animation.SequenceAnimation;
import com.mpJavaGame.game.Renderer;
import com.mpJavaGame.gfx.Texture;
import com.mpJavaGame.interfaces.Renderable;

public class AnimatedBackground extends Background implements Renderable {

    private Animation animation;

    public AnimatedBackground(Texture images[], int delayMS, int width, int height) {
        super(width, height);
        animation = new SequenceAnimation(delayMS, images);
    }

    @Override
    public void update(float delta) {
        animation.update();
    }

    @Override
    public void render(Renderer g) {
        g.draw(animation.getCurrentSprite(), 0, 0);
    }
}
