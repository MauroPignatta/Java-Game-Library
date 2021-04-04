package com.mpJavaGame.gfx.background;

import com.mpJavaGame.game.Renderer;
import com.mpJavaGame.gfx.Texture;
import com.mpJavaGame.interfaces.Renderable;
import com.mpJavaGame.math.Vector2F;

public class MovingBackground extends Background implements Renderable {

    private boolean running;
    private Vector2F direction;
    private Vector2F velocity;
    private Vector2F bg1;
    private Vector2F bg2;

    private Texture background;

    public MovingBackground(Texture background, int width, int height, float sx, float sy) {
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

    @Override
    public void render(Renderer g) {
        g.draw(background, bg1.getIntX(), bg1.getIntY());
        g.draw(background, bg2.getIntX(), bg2.getIntY());
    }

    private boolean isOffScreen(Vector2F v) {
        return v.getIntX() + width <= 1 || v.getIntX() >= width ||
                v.getIntY() + height <= 1 || v.getIntY() >= height;
    }

    public void setImage(Texture image) {
        this.background = image;
    }

}
