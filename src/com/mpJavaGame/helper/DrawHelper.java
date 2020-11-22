package com.mpJavaGame.helper;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class DrawHelper {

    public static void drawRotated(Graphics2D g, BufferedImage image, int x, int y, int width, int height, float angle) {
        AffineTransform backup = g.getTransform();
        AffineTransform a = new AffineTransform(backup);
        a.rotate(angle, x + (width >> 1), y + (height >> 1));
        g.setTransform(a);
        g.drawImage(image, x, y, width, height, null);
        g.setTransform(backup);
    }

}
