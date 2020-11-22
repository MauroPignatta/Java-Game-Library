package com.mpJavaGame.ui.objects;

import java.awt.*;

abstract class UILabel extends UIObject {

    protected UILabel(Rectangle bounds) {
        super(bounds);
    }

    @Override
    public abstract void render(Graphics2D g);
}
