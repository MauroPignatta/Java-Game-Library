package com.mpJavaGame.gfx;

import com.mpJavaGame.helper.ImageHelper;

public class ImageTexture extends Texture {

    public ImageTexture(String path) {
        super(ImageHelper.loadImage(path));
    }

}
