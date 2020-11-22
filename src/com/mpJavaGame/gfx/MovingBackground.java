package com.mpJavaGame.gfx;

import com.mpJavaGame.interfaces.Renderable;
import com.mpJavaGame.math.Vector2F;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MovingBackground extends Background implements Renderable {

    private boolean running;
    private Vector2F direction;
    private Vector2F velocity;
    private Vector2F bg1;
    private Vector2F bg2;

    private BufferedImage background;

    public MovingBackground(BufferedImage background, int width, int height, float sx, float sy) {
        super(width, height);
        this.background = background;
        this.velocity = new Vector2F(sx, sx == 0 ? sy : 0);
        this.direction = velocity.direction();
        bg1 = new Vector2F(0, 0);

        if (sx != 0) {
            bg2 = new Vector2F(sx < 0 ? width : -width, 0);
        } else {
            bg2 = new Vector2F(0, sy < 0 ? height : -height);
        }
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }


    public void update(float delta) {
        if (!running)
            return;

        if (isOffScreen(bg1)) {
            if (velocity.getX() != 0) {
                bg1.setX(direction.getX() > 0 ? -width : width);
            } else {
                bg1.setY(direction.getY() < 0 ? height : -height);
            }
        }

        if (isOffScreen(bg2)) {
            if (velocity.getX() != 0) {
                bg2.setX(direction.getX() > 0 ? -width : width);
            } else {
                bg2.setY(direction.getY() < 0 ? height : -height);
            }
        }

        bg1.add(velocity.multiplyV2F(delta));
        bg2.add(velocity.multiplyV2F(delta));

    }

    private boolean isOffScreen(Vector2F v) {
        return v.getIntX() + width <= 1 || v.getIntX() >= width ||
                v.getIntY() + height <= 1 || v.getIntY() >= height;
    }


    @Override
    public void render(Graphics2D g) {
        drawBackground(bg1, g);
        drawBackground(bg2, g);
    }

    private void drawBackground(Vector2F bg, Graphics2D g) {
        g.drawImage(background, bg.getIntX(), bg.getIntY(), width, height, null);
    }


    public void setImage(BufferedImage image) {
        this.background = image;
    }

}
