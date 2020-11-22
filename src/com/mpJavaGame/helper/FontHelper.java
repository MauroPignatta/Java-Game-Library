package com.mpJavaGame.helper;

import java.awt.*;
import java.io.IOException;

public class FontHelper {

    public static Font createFont(String path, float size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, FontHelper.class.getResourceAsStream(path)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return font;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
