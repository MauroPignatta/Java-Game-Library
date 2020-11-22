package com.mpJavaGame.ui.objects;

import com.mpJavaGame.input.MouseInput;

import java.awt.*;

public abstract class UIButton extends UIObject {

    protected UIButton(Rectangle bounds) {
        super(bounds);
    }

    protected abstract void onClick();

    @Override
    public void update() {
        update(bounds.contains(MouseInput.getMouse().getMouseLocation()));
    }

    @Override
    public abstract void render(Graphics2D g);

    protected abstract void update(boolean isHover);
}

