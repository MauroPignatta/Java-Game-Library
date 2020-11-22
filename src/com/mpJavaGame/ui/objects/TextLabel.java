package com.mpJavaGame.ui.objects;

import java.awt.*;

public class TextLabel extends UILabel {

    private Color color;
    private String text;

    private Font font;
    private FontMetrics metrics;
    private float alignment;

    private int x, y;
    private Point drawPoint;

    public TextLabel(int x, int y, float alignment, Color color, Font font, String text) {
        super(new Rectangle(x, y, 0, 0));
        this.x = x;
        this.y = y;
        this.alignment = Math.min(Math.max(alignment, 0f), 1f);
        this.text = text;
        this.color = color;
        this.font = font;
    }

    @Override
    protected void update() {
    }

    @Override
    public void render(Graphics2D g) {
        draw(g);
    }

    private void draw(Graphics2D g) {
        if (metrics == null) {
            metrics = g.getFontMetrics(font);
            drawPoint = metrics();
        }

        g.setColor(color);
        g.setFont(font);
        g.drawString(text, drawPoint.x, drawPoint.y);
    }

    public void setText(String text) {
        this.text = text;
        drawPoint = metrics();
    }

    private Point metrics() {
        Point p = null;
        if (metrics != null) {
            p = new Point(x, y);
            p.x = x - (int) (metrics.stringWidth(text) * alignment);
            p.y = (y - metrics.getHeight() / 2) + metrics.getAscent();
        }
        return p;
    }
}
