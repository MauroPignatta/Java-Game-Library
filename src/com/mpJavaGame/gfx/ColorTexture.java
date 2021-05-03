package com.mpJavaGame.gfx;

import java.awt.*;

import static com.mpJavaGame.helper.ImageHelper.createImage;

public class ColorTexture extends Texture {

    public ColorTexture(Color color, int width, int height) {
        super(createImage(color.getRGB(), width, height));
    }

}
