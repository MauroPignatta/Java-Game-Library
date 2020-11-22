package com.mpJavaGame.ui.objects;

import java.awt.*;

abstract class UIObject {

    protected Rectangle bounds;

    protected UIObject(Rectangle bounds) {
        this.bounds = bounds;
    }

    protected abstract void update();

    protected abstract void render(Graphics2D g);

    public int getX() {
        return bounds.x;
    }

    public int getY() {
        return bounds.y;
    }

    public int getWidth() {
        return bounds.width;
    }

    public int getHeight() {
        return bounds.height;
    }

    public void setX(int x) {
        bounds.x = x;
    }

    public void setY(int y) {
        bounds.y = y;
    }

    public void setWidth(int width) {
        bounds.width = width;
    }

    public void setHeight(int height) {
        bounds.height = height;
    }

}
