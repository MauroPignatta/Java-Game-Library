package com.mpJavaGame.input;

import com.mpJavaGame.math.Vector2F;
import com.mpJavaGame.game.GameWindow;

import java.awt.*;
import java.awt.event.MouseListener;

public class MouseInput {

    private static final int UP = -1;
    private static final int DOWN = 1;

    private static MouseInput mouse;
    private Mouse input;

    private MouseInput() {
        input = new Mouse();
    }

    public static MouseInput getMouse() {
        if (mouse == null)
            mouse = new MouseInput();

        return mouse;
    }

    public Point getMouseLocation() {
        Point p = new Point(input.getLocation());
        p.x /= GameWindow.sx;
        p.y /= GameWindow.sy;
        return p;
    }

    public Vector2F getMouseLocationV2F() {
        Vector2F v = new Vector2F(input.getLocation());
        v.divide(GameWindow.sx, GameWindow.sy);
        return v;
    }

    public void update() {
        input.update();
    }

    public boolean isScrollingUp() {
        return input.getScroll(UP) < 0;
    }

    public boolean isScrollingDown() {
        return input.getScroll(DOWN) > 0;
    }

    public boolean isButtonUp(MouseButton button) {
        return input.isButtonUp(button);
    }

    public boolean isButtonDown(MouseButton button) {
        return input.isButtonDown(button);
    }

    public boolean isButtonPressed(MouseButton button) {
        return input.isButtonPressed(button);
    }

    public MouseListener getListener() {
        return input;
    }

}
