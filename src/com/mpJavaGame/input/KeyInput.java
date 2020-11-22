package com.mpJavaGame.input;

import java.awt.event.KeyListener;

public class KeyInput {

    private static KeyInput keyboard;

    private Keys keys;
    private boolean disabled;

    private KeyInput() {
        keys = new Keys();
    }

    public static KeyInput getKeyboard() {
        if (keyboard == null)
            keyboard = new KeyInput();

        return keyboard;
    }

    public void update(){
        keys.update();
    }

    /**
     * Indicates whether a key is being pressed or not.
     *
     * @param keycode KeyEvent keycode constant.
     * @return true if it's being pressed, false if not.
     */
    public boolean isKeyPressed(int keycode) {
        return !disabled && keys.isKeyPressed(keycode);
    }

    public boolean isKeyUp(int keycode) {
        return !disabled && keys.isKeyUp(keycode);
    }

    public boolean isKeyDown(int keycode) {
        return !disabled && keys.isKeyDown(keycode);
    }

    /**
     * Disables the keyboard.
     */
    public void disable() {
        keys.reset();
        disabled = true;
    }

    public void enable() {
        disabled = false;
    }

    public KeyListener getKeyListener() {
        return keys;
    }
}
