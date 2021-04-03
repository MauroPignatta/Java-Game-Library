package com.mpJavaGame.helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHelper {

    public static final int MIRROR_LEFT = 1;
    public static final int MIRROR_UP = 2;

    /**
     * Loads an image.
     *
     * @param path path to the file.
     * @return loaded image or null if couldn't load the image
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageHelper.class.getResource(path));
        } catch (IOException e) {
            System.err.println("Failed to load: " + path);
        }
        return null;
    }

    /**
     * Returns a mirrored image of an image.
     *
     * @param img        image to mirror
     * @param mirrorMode indicates whether you want mirror an image left-right or up-down.
     *                   Possible values are constants of this class.
     * @return mirrored image or null if the source is null or the mirrorMode is not a valid value.
     */
    public static BufferedImage mirrorImage(BufferedImage img, int mirrorMode) {
        if (img == null)
            return null;
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, img.getType());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (mirrorMode) {
                    case MIRROR_LEFT:
                        newImage.setRGB(x, y, img.getRGB(height - 1 - x, y));
                        break;
                    case MIRROR_UP:
                        newImage.setRGB(x, y, img.getRGB(x, height - y - 1));
                        break;
                    default:
                        return null;
                }
            }
        }
        return newImage;
    }

    public static BufferedImage createImage(int rgb, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < height; ++i){
            for(int j = 0; j < width; ++j){
                image.setRGB(j, i, rgb);
            }
        }
        return image;
    }

    public static BufferedImage[] loadImages(String path, int i, int n, String extension) {
        if (n <= 0 || i > n)
            return null;
        BufferedImage[] images = new BufferedImage[n - i + 1];
        int j = 0;
        while (i <= n) {
            images[j++] = loadImage(path + (i++) + extension);
        }
        return images;
    }

    public static void saveImage(BufferedImage image, String extension, String path) {
        try {
            File file = new File(path);
            ImageIO.write(image, extension, file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
