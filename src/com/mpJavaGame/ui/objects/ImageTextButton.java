package com.mpJavaGame.ui.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ImageTextButton extends TextButton {

    private BufferedImage hoverImg;
    private BufferedImage image;
    private int buttonWidth;
    private boolean reBound;

    public ImageTextButton(BufferedImage image, int imgWidth, int x, int y, float alignment, Color color, Font font, String text) {
        super(x, y, alignment, color, font, text);
        this.image = image;
        this.buttonWidth = imgWidth;
    }

    public ImageTextButton(BufferedImage image, int imgWidth, int x, int y, float alignment, Color normal, Color hover, Font font, String text) {
        super(x, y, alignment, normal, hover, font, text);
        this.image = image;
        this.buttonWidth = imgWidth;
    }

    public ImageTextButton(BufferedImage image, BufferedImage hoverImage, int imgWidth, int x, int y, float alignment, Color color, Font font, String text) {
        this(image, imgWidth, x, y, alignment, color, color, font, text);
        this.hoverImg = hoverImage;
    }

    public ImageTextButton(BufferedImage image, BufferedImage hoverImage, int imgWidth, int x, int y, float alignment, Color color, Color hover, Font font, String text) {
        this(image, imgWidth, x, y, alignment, color, hover, font, text);
        this.hoverImg = hoverImage;
    }

    @Override
    public void render(Graphics2D g) {
        metrics(g);
        if (!reBound) {
            rebound();
        }

        Rectangle r = getBounds();

        if (!isHover || hoverImg == null)
            g.drawImage(image, r.x, r.y + getDescent(), r.width, r.height, null);
        else
            g.drawImage(hoverImg, r.x, r.y + getDescent(), r.width, r.height, null);
        super.render(g);
    }

    private void rebound() {
        Rectangle r = getBounds();
        int offset = buttonWidth < r.width ? 0 : (buttonWidth - r.width) / 2;
        setX(r.x - offset);
        setWidth(r.width + offset * 2);
        reBound = true;
    }
}
