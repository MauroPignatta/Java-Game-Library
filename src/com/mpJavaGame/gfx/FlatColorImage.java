package com.mpJavaGame.gfx;

import java.awt.*;

import static com.mpJavaGame.helper.ImageHelper.createImage;

public class FlatColorImage extends Image {

    public FlatColorImage(Color color, int width, int height) {
        super(createImage(color.getRGB(), width, height));
    }

}
