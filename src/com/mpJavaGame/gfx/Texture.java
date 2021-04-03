package com.mpJavaGame.gfx;

import com.mpJavaGame.helper.ImageHelper;

public class Texture extends Image{
    
    public Texture(String path) {
        super(ImageHelper.loadImage(path));
    }

}
