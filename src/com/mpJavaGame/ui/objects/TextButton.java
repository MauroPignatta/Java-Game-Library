package com.mpJavaGame.ui.objects;

import com.mpJavaGame.input.MouseButton;
import com.mpJavaGame.input.MouseInput;

import java.awt.*;

public abstract class TextButton extends UIButton {

    protected boolean isHover;
    private Color normal;
    private Color hover;
    private String text;

    private Font font;
    private FontMetrics metrics;

    private int x, y;
    private Point drawPoint;
    private float alignment;

    public TextButton(int x, int y, float alignment, Color color, Font font, String text) {
        this(x, y, alignment, color, color, font, text);
    }

    public TextButton(int x, int y, float alignment, Color normal, Color hover, Font font, String text) {
        super(new Rectangle(x, y, 0, 0));
        this.text = text;
        this.normal = normal;
        this.hover = hover;
        this.font = font;
        this.alignment = alignment;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void update(boolean isHover) {
        this.isHover = isHover;
        if (isHover && MouseInput.getMouse().isButtonDown(MouseButton.LEFT)) {
            onClick();
        }
    }

    @Override
    public void render(Graphics2D g) {
        draw(g);
    }

    private void draw(Graphics2D g) {

        metrics(g);

        if (isHover) {
            g.setColor(hover);
        } else {
            g.setColor(normal);
        }

        g.setFont(font);
        g.drawString(text, drawPoint.x, drawPoint.y);
    }

    protected void metrics(Graphics2D g) {
        if (metrics == null) {
            metrics = g.getFontMetrics(font);

            drawPoint = new Point(x, y);
            drawPoint.x = x - (int) (metrics.stringWidth(text) * alignment);
            drawPoint.y = (y - metrics.getHeight() / 2) + metrics.getAscent();

            bounds.x = drawPoint.x;
            bounds.y = drawPoint.y - metrics.getHeight();
            bounds.width = metrics.stringWidth(text);
            bounds.height = metrics.getHeight();
        }
    }

    protected Rectangle getBounds() {
        return bounds;
    }

    public int getDescent() {
        return metrics.getDescent();
    }

}
