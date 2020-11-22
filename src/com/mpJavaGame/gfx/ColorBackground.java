package com.mpJavaGame.gfx;

import java.awt.*;

public class ColorBackground extends Background {

    private Color color;

    public ColorBackground(Color color, int width, int height) {
        super(width, height);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawRect(0, 0, width, height);
    }
}
