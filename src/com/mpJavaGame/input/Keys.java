package com.mpJavaGame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

class Keys implements KeyListener {

    private boolean keys[];
    private boolean lastKeys[];

    public Keys() {
        keys = new boolean[256];
        lastKeys = new boolean[256];
    }

    public void update() {
        System.arraycopy(keys, 0, lastKeys, 0, keys.length);
    }

    public boolean isKeyPressed(int keycode) {
        return keys[keycode];
    }

    public boolean isKeyUp(int keycode) {
        return !keys[keycode] && lastKeys[keycode];
    }

    public boolean isKeyDown(int keycode) {
        return keys[keycode] && !lastKeys[keycode];
    }

    public void reset() {
        Arrays.fill(keys, false);
        Arrays.fill(lastKeys, false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
