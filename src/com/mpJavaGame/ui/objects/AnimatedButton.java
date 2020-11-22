package com.mpJavaGame.ui.objects;

import com.mpJavaGame.animation.Animation;
import com.mpJavaGame.input.MouseButton;
import com.mpJavaGame.input.MouseInput;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AnimatedButton extends UIButton {

    private boolean isHover;

    private BufferedImage buttonImage;
    private Animation animation;

    public AnimatedButton(Rectangle bounds, BufferedImage unhoverImage, Animation hoverAnimation) {
        super(bounds);
        animation = hoverAnimation;
        buttonImage = unhoverImage;
    }

    @Override
    public void update(boolean isHover) {
        if (isHover)
            animation.update();
        else
            animation.reset();

        this.isHover = isHover;
        if (isHover && MouseInput.getMouse().isButtonDown(MouseButton.LEFT)) {
            onClick();
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (isHover) {
            g.drawImage(animation.getCurrentSprite(), bounds.x, bounds.y, bounds.width, bounds.height, null);
        } else {
            g.drawImage(buttonImage, bounds.x, bounds.y, bounds.width, bounds.height, null);
        }
    }
}
