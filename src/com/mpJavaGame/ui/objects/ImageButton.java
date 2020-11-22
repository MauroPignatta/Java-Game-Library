package com.mpJavaGame.ui.objects;

import com.mpJavaGame.input.MouseButton;
import com.mpJavaGame.input.MouseInput;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ImageButton extends UIButton {

    private boolean isHover;

    private BufferedImage normal;
    private BufferedImage hover;

    public ImageButton(Rectangle bounds, BufferedImage defaultImage) {
        this(bounds, defaultImage, defaultImage);
    }

    public ImageButton(Rectangle bounds, BufferedImage defaultImage, BufferedImage hoverImage) {
        super(bounds);
        normal = defaultImage;
        hover = hoverImage;
    }

    @Override
    public void update(boolean isHover) {
        this.isHover = isHover;
        if (isHover && MouseInput.getMouse().isButtonDown(MouseButton.LEFT)) {
            onClick();
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (isHover) {
            g.drawImage(hover, bounds.x, bounds.y, bounds.width, bounds.height, null);
        } else {
            g.drawImage(normal, bounds.x, bounds.y, bounds.width, bounds.height, null);
        }
    }


}
