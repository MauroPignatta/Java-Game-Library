package com.mpJavaGame.gfx.background;

import com.mpJavaGame.animation.Animation;
import com.mpJavaGame.animation.SequenceAnimation;
import com.mpJavaGame.game.Renderer;
import com.mpJavaGame.gfx.Texture;

public class MovingAnimatedBackground extends MovingBackground {

    private Animation animation;

    public MovingAnimatedBackground(Texture images[], int delayMS, int width, int height, float sx, float sy) {
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
    public void render(Renderer g) {
        super.render(g);
    }
}
